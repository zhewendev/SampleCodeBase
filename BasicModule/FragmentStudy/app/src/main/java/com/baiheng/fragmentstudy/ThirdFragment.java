package com.baiheng.fragmentstudy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {
    private static final String TAG = ThirdFragment.class.getSimpleName();

    private Activity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View root = inflater.inflate(R.layout.fragment_third,container,false);
        Bundle bundle = getArguments();
        resolveArgument(bundle);
        return root;
    }

    private void resolveArgument(Bundle bundle) {
        if (bundle != null) {
            String param = bundle.getString("param");
            Toast.makeText(mActivity,param,Toast.LENGTH_LONG).show();
        }
    }

    public static ThirdFragment newInstance(String text) {
        ThirdFragment thirdFragment = new ThirdFragment();
        Bundle bundle = new Bundle();
        bundle.putString("param",text);
        thirdFragment.setArguments(bundle);
        return thirdFragment;
    }
}
