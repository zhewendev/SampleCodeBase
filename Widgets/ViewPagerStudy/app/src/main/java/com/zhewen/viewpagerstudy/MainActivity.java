package com.zhewen.viewpagerstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private ViewPager vpager_two;
//    private ArrayList<View> aList;
//    private ArrayList<String> sList;
//    private MyPagerAdapter mAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_three);
//        vpager_two = (ViewPager) findViewById(R.id.vpager_three);
//        aList = new ArrayList<View>();
//        LayoutInflater li = getLayoutInflater();
//        aList.add(li.inflate(R.layout.view_one,null,false));
//        aList.add(li.inflate(R.layout.view_two,null,false));
//        aList.add(li.inflate(R.layout.view_three,null,false));
//        sList = new ArrayList<String >();
//        sList.add("橘黄");
//        sList.add("淡黄");
//        sList.add("浅棕");
//        mAdapter = new MyPagerAdapter(aList,sList);
//        vpager_two.setAdapter(mAdapter);
//    }

    private int[] drawableIds = new int[] { R.drawable.koala, R.drawable.desert,
            R.drawable.hydran, R.drawable.aaa, R.drawable.ddd };
    private List<View> viewList = new ArrayList<>();
    private ViewPager viewPager;
    private List<Integer> drawableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.vpager_one);
        initData();
        createPageItems();
        viewPager.setAdapter(new CyclePagerAdapter(viewList, drawableList));
        viewPager.addOnPageChangeListener(new CycleScrollOnPageChangeListener(viewPager, viewList));
        viewPager.setCurrentItem(viewList.size() - 2, false);
        viewPager.setVisibility(View.INVISIBLE);
        viewPager.setOffscreenPageLimit(0);
        viewPager.postDelayed(new Runnable() {

            @Override
            public void run() {
                viewPager.setVisibility(View.VISIBLE);
                // 设置初始 position
                viewPager.setCurrentItem(1, false);
            }
        }, 100);



    }

    private void initData() {
        drawableList = new ArrayList<Integer>();
        drawableList.add(drawableIds[drawableIds.length - 1]);
        for (int id : drawableIds) {
            drawableList.add(id);
        }
        drawableList.add(drawableIds[0]);
    }

    public void createPageItems() {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < drawableList.size(); i++) {
            View view = inflater.inflate(R.layout.viewpager_item, null);
            viewList.add(view);
        }
    }
}
