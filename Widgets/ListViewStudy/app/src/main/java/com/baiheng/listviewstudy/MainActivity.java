package com.baiheng.listviewstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView学习理解代码工程主活动
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private List<Fruit> mFruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        setListView();

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

    private void setListView() {
        FruitAdapter fruitAdapter = new FruitAdapter(this);
        fruitAdapter.setSourceData(mFruitList);
        ListView listView = findViewById(R.id.list_view);
        final LayoutInflater inflater = LayoutInflater.from(this);
        View headView = inflater.inflate(R.layout.list_view_header_item,null,false);
        View footerView = inflater.inflate(R.layout.list_view_footer_item,null,false);
        listView.addHeaderView(headView);
        listView.addFooterView(footerView);
        listView.setAdapter(fruitAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruit fruit = mFruitList.get(i);
                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }


}
