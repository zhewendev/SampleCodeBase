package com.zhewen.viewpagerstudy;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class CyclePagerAdapter extends PagerAdapter {
    private List<View> viewList;
    private List<Integer> drawableList;
    public CyclePagerAdapter() {

    }

    public CyclePagerAdapter(List<View> viewList, List<Integer> drawableList) {
        this.viewList = viewList;
        this.drawableList = drawableList;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = viewList.get(position);
        ImageView image_view = (ImageView) view.findViewById(R.id.imageView);
        image_view.setImageResource(drawableList.get(position));
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(String.valueOf(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }
}
