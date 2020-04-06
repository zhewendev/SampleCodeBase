package com.baiheng.viewslidestudy;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 自定义View，通过layout()设置View滑动
 */
public class SlideByLayoutView extends View {

    private static final String TAG = SlideByLayoutView.class.getSimpleName();

    private int mLastTouchPointX;
    private int mLastTouchPointY;

    public SlideByLayoutView(Context context) {
        super(context);
    }

    public SlideByLayoutView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public SlideByLayoutView(Context context, AttributeSet attr, int defStyleAttr) {
        super(context, attr, defStyleAttr);
    }

    /**
     * 获取触摸点坐标，通过layout()实现滑动
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
                layout(getLeft() + offsetX, getTop() + offsetY,
                        getRight() + offsetX, getBottom() + offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
