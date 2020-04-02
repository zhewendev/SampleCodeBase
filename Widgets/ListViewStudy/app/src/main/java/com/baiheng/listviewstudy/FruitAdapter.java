package com.baiheng.listviewstudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * 自定义适配器
 */
public class FruitAdapter extends BaseAdapter {
    private static final String TAG = FruitAdapter.class.getSimpleName();

    private Context mContext;
    private List<Fruit> mFruitDataList;

    public FruitAdapter(Context context) {
        mContext = context;
    }

    public void setSourceData(List<Fruit> list) {
        mFruitDataList = list;
    }


    @Override
    public int getCount() {
        return mFruitDataList != null ? mFruitDataList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_view_item,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.fruitImage.setImageResource(mFruitDataList.get(position).getImageId());
        viewHolder.fruitName.setText(mFruitDataList.get(position).getName());
        return view;
    }
    class ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
    }
}
