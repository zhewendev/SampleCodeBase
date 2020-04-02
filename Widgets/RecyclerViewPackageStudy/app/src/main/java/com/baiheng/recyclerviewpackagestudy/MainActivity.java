package com.baiheng.recyclerviewpackagestudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private List<Fruit> mFruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        setRecyclerView();
    }

    private void initFruits() {
        mFruitList.add(new Fruit("Apple",R.drawable.apple_pic));
        mFruitList.add(new Fruit("Banana",R.drawable.banana_pic));
        mFruitList.add(new Fruit("Orange",R.drawable.orange_pic));
        mFruitList.add(new Fruit("Watermelon",R.drawable.watermelon_pic));
        mFruitList.add(new Fruit("Pear",R.drawable.pear_pic));
        mFruitList.add(new Fruit("Grape",R.drawable.grape_pic));
        mFruitList.add(new Fruit("Pineapple",R.drawable.pineapple_pic));
        mFruitList.add(new Fruit("Strawberry",R.drawable.strawberry_pic));
        mFruitList.add(new Fruit("Cherry",R.drawable.cherry_pic));
        mFruitList.add(new Fruit("Mango",R.drawable.mango_pic));
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter fruitAdapter = new FruitAdapter(this,mFruitList);
        recyclerView.setAdapter(fruitAdapter);
    }

}
