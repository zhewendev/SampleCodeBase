package com.baiheng.customviewgroupstudy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义ViewGroup
 */
public class CustomViewGroup extends ViewGroup {

    private Context mContext;

    public CustomViewGroup(Context context) {
        super(context);
        mContext = context;
    }
    public CustomViewGroup(Context context, AttributeSet attr) {
        super(context,attr);
        mContext = context;
    }

    /**
     * 获取子View的属性
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(mContext, attrs);
    }

    /**
     * 计算所有childView的宽高，根据计算结果设置宽高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 根据传入的参数，获取测量模式和测量值
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        // 记录如果是wrap_content时设置的宽高
        int width = 0;
        int height = 0;
        int childViewCount = getChildCount();
        int childViewWidth = 0;
        int childViewHeight = 0;
        MarginLayoutParams childViewParams = null;

        // 用于计算左右两边childView的高度，取最大值
        int leftHeight = 0;
        int rightHeight = 0;

        // 用于计算上下边childView的宽度，取最大值
        int topWidth = 0;
        int bottomWidth = 0;

        /**
         * 根据childView计算的出的宽和高，以及设置的margin计算容器的宽和高，主要用于容器是warp_content时
         */
        for (int i = 0; i < childViewCount; i++) {
            View childView = getChildAt(i);
            childViewWidth = childView.getMeasuredWidth();
            childViewHeight = childView.getMeasuredHeight();
            childViewParams = (MarginLayoutParams) childView.getLayoutParams();

            // 上面两个childView
            if (i == 0 || i == 1) {
                topWidth += childViewWidth + childViewParams.leftMargin + childViewParams.rightMargin;
            }

            if (i == 2 || i == 3) {
                bottomWidth += childViewWidth + childViewParams.leftMargin + childViewParams.rightMargin;
            }

            if (i == 0 || i == 2) {
                leftHeight += childViewHeight + childViewParams.topMargin + childViewParams.bottomMargin;
            }

            if (i == 1 || i == 3) {
                rightHeight += childViewHeight + childViewParams.topMargin + childViewParams.bottomMargin;
            }

        }

        width = Math.max(topWidth, bottomWidth);
        height = Math.max(leftHeight, rightHeight);

        /**
         * 如果是wrap_content设置为我们计算的值
         * 否则：直接设置为父容器计算的值
         */
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
                : width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
                : height);

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

        int childCount = getChildCount();   // 计算所有childView的个数
        int childViewWidth = 0;
        int chilViewHeight = 0;
        MarginLayoutParams chilViewParams = null;
        /**
         * 遍历所有childView根据其宽和高，以及margin进行布局
         */
        for (int j = 0; j < childCount; j++)
        {
            View childView = getChildAt(j);
            childViewWidth = childView.getMeasuredWidth();
            chilViewHeight = childView.getMeasuredHeight();
            chilViewParams = (MarginLayoutParams) childView.getLayoutParams();

            int chilViewLeft = 0, chilViewTop = 0, chilViewRight = 0, chilViewBottom = 0;

            switch (j)
            {
                case 0:
                    chilViewLeft = chilViewParams.leftMargin;
                    chilViewTop = chilViewParams.topMargin;
                    break;
                case 1:
                    chilViewLeft = getWidth() - childViewWidth - chilViewParams.leftMargin
                            - chilViewParams.rightMargin;
                    chilViewTop = chilViewParams.topMargin;

                    break;
                case 2:
                    chilViewLeft = chilViewParams.leftMargin;
                    chilViewTop = getHeight() - chilViewHeight - chilViewParams.bottomMargin;
                    break;
                case 3:
                    chilViewLeft = getWidth() - childViewWidth - chilViewParams.leftMargin
                            - chilViewParams.rightMargin;
                    chilViewTop = getHeight() - chilViewHeight - chilViewParams.bottomMargin;
                    break;

            }
            chilViewRight = chilViewLeft + childViewWidth;
            chilViewBottom = chilViewHeight + chilViewTop;
            childView.layout(chilViewLeft, chilViewTop, chilViewRight, chilViewBottom);
        }
    }
}
