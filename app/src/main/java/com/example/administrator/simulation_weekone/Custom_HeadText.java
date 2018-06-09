package com.example.administrator.simulation_weekone;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/6/9.
 */

public class Custom_HeadText extends ViewGroup {
    public Custom_HeadText(Context context) {
        super(context);
    }

    public Custom_HeadText(Context context, AttributeSet attrs) {
        this(context, attrs,00);
    }

    public Custom_HeadText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量所有孩子的宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //最大的宽度，也就是FlowLayout父布局的宽度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //宽
        int width = 0;
        //高
        int height = 0;
        //子控件宽度之和
        int lineWidth = 0;
        //子控件高度之和
        int lineHeight = 0;

        int totalHeight = 0;
        View childView;
        //子控件宽度
        int childWidth = 0;
        //子控件高度
        int childHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            childView = getChildAt(i);
            childWidth = childView.getMeasuredWidth();
            if (childWidth > widthSize) {
                throw new IllegalArgumentException("子view宽度不能大于FlowLayout宽度");
            }
            childHeight = childView.getMeasuredHeight();
            if (lineWidth + childWidth > widthSize) {
                //换行width就是最大宽,只要又换行的情况出现，说明流式布局的宽度已经不够用了
                width = widthSize;
                lineWidth = childWidth;
                totalHeight+=lineHeight =Math.max(lineHeight,childHeight);
            } else {
                lineWidth += childWidth;
                totalHeight+=lineHeight = Math.max(lineHeight, childHeight);
                width = Math.max(width, lineWidth);
            }
            if (i == getChildCount() - 1) {
                totalHeight += lineHeight;
                height = totalHeight;
            }

        }

        width = widthMode == MeasureSpec.EXACTLY ? widthSize : width;
        height = heightMode == MeasureSpec.EXACTLY ? heightSize : height;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean bo, int left, int top, int right, int bottom) {

        int lineWidth = 0;
        int lineHeight = 0;
        int totalHeight = 0;
        View childView;
        int childWidth = 0;
        int childHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            childView = getChildAt(i);
            childWidth = childView.getMeasuredWidth();
            childHeight = childView.getMeasuredHeight();
            if (lineWidth + childWidth > getMeasuredWidth()) {
                //换行
                //totalHeight不包含最后一行高度

                layoutChildView(childView,0, totalHeight,childWidth,totalHeight + childHeight);
                //换行width就是最大宽
               totalHeight+=childHeight;
                lineWidth = childWidth;
            } else {
                //不换行
                layoutChildView(childView, lineWidth, totalHeight, lineWidth + childWidth, totalHeight + childHeight);
                lineWidth += childWidth;
                //当前行的高度
               totalHeight+=childHeight;
            }
        }
    }
    public void layoutChildView(View child, int l, int t, int r, int b) {
        child.layout(l, t, r, b);
    }
}

