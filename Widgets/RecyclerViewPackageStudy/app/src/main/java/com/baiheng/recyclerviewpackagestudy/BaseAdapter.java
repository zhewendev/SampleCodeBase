package com.baiheng.recyclerviewpackagestudy;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * 通用适配器
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    private Context mContext;
    private List<T> mData;

    public BaseAdapter(Context context,List<T> data) {
        mContext = context;
        mData = data;
    }

    public abstract int getLayoutId(int viewType);

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.get(parent,getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        convert(viewHolder,mData.get(position),position);
    }

    public abstract void convert(ViewHolder viewHolder, T data, int position);

    static class ViewHolder extends RecyclerView.ViewHolder {

        private View mConvertView;
        private SparseArray<View> mViews;

        private ViewHolder(View v) {
            super(v);
            mConvertView = v;
            mViews = new SparseArray<>();
        }

        public static ViewHolder get(ViewGroup parent, int layoutId) {
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent, false);
            return new ViewHolder(convertView);
        }

        public <T extends View> T getView(int id) {
            View v = mViews.get(id);
            if (v == null) {
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (T) v;
        }

        public void setText(int id, String value) {
            TextView view = getView(id);
            view.setText(value);
        }
    }

}
