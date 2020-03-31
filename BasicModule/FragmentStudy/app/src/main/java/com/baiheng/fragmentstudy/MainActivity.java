package com.baiheng.fragmentstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

/**
 * Fragment的主活动页面
 *
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dynamicLoadFragment(LeftFragment.newInstance("Hello, I'm MainActivity"));
        dynamicLoadFragment(new RightFragment());
    }

    private void dynamicLoadFragment(Fragment fragment) {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.fragment_contain,fragment);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }
}
