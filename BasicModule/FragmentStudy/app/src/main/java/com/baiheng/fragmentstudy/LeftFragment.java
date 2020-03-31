package com.baiheng.fragmentstudy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class LeftFragment extends Fragment {
    private static final String TAG = LeftFragment.class.getSimpleName();
    private static String ARG_PARAM = "param_key";
    private String mParam;
    private Activity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        assert getArguments() != null;
        mParam = getArguments().getString(ARG_PARAM);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View root = inflater.inflate(R.layout.fragment_left,container,false);
        TextView textView = (TextView) root.findViewById(R.id.tv_left_fragment);
        textView.setText(mParam);
        return root;
    }

    public static LeftFragment newInstance(String string) {
        LeftFragment leftFragment = new LeftFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM,string);
        leftFragment.setArguments(bundle);
        return leftFragment;
    }
}
