package com.zhewen.viewpagerstudy;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MyPagerAdapter extends PagerAdapter {

    private ArrayList<View> viewLists;
    private ArrayList<String> titleLists;
    public MyPagerAdapter() {

    }

    public MyPagerAdapter(ArrayList<View> viewLists,ArrayList<String> titleLists) {
        super();
        this.viewLists = viewLists;
        this.titleLists = titleLists;
    }

    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewLists.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleLists.get(position);
    }
}
