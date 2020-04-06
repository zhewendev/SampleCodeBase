package com.baiheng.viewslidestudy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 通过修改View的LayoutParams来修改View的位置，实现滑动
 */
public class SlideByLayoutParamsView extends View {

    private int mLastTouchPointX;
    private int mLastTouchPointY;

    public SlideByLayoutParamsView(Context context) {
        super(context);
    }

    public SlideByLayoutParamsView(Context context, AttributeSet attr) {
        super(context,attr);
    }

    public SlideByLayoutParamsView(Context context, AttributeSet attr, int defStyleAttr) {
        super(context,attr,defStyleAttr);
    }

    /**
     *获取触摸点坐标，修改layoutParams
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int touchPointX = (int) event.getX();
        int touchPointY = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastTouchPointX = touchPointX;
                mLastTouchPointY = touchPointY;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = touchPointX - mLastTouchPointX;
                int offsetY = touchPointY - mLastTouchPointY;
                moveView(offsetX,offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

    private void moveView(int offsetX, int offsetY) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        layoutParams.leftMargin = getLeft() + offsetX;
        layoutParams.topMargin = getTop() + offsetY;
        setLayoutParams(layoutParams);
    }
}
