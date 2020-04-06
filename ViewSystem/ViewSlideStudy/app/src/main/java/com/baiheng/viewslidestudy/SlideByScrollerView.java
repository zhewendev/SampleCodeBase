package com.baiheng.viewslidestudy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 自定义View，通过Scroller实现View的滑动
 */
public class SlideByScrollerView extends View {
    private int mLastTouchPointX;
    private int mLastTouchPointY;

    public SlideByScrollerView(Context context) {
        super(context);
    }

    public SlideByScrollerView(Context context, AttributeSet attr) {
        super(context,attr);
    }

    public SlideByScrollerView(Context context, AttributeSet attr, int defStyleAttr) {
        super(context,attr,defStyleAttr);
    }

    /**
     *
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
                ((View)getParent()).scrollBy(-offsetX,-offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

    /**
     * 实现弹性滑动内部逻辑
     */
    @Override
    public void computeScroll() {
        super.computeScroll();

    }
}
