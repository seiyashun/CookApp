package com.tuongco.nauan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tuongco.nauan.R;
import com.tuongco.nauan.model.Loaimonan;

import java.util.ArrayList;

/**
 * Created by Administrator on 03/10/2017.
 */

public class LoaimonanAdapter extends BaseAdapter {
    ArrayList<Loaimonan> arrLMA;
    Context context;

    public LoaimonanAdapter(ArrayList<Loaimonan> arrLMA, Context context) {
        this.arrLMA = arrLMA;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrLMA.size();
    }

    @Override
    public Object getItem(int i) {
        return arrLMA.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public  class ViewHolder{
        TextView txttenloaimonan;
        ImageView imageviewloaimonan;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view==null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaimonan,null);
            viewHolder.txttenloaimonan = (TextView) view.findViewById(R.id.textviewloaimonan);
            viewHolder.imageviewloaimonan = (ImageView) view.findViewById(R.id.imageviewloaimonan);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();

        }
        Loaimonan loaimonan = (Loaimonan) getItem(i);
        viewHolder.txttenloaimonan.setText(loaimonan.getTenloai());
        Picasso.with(context).load(loaimonan.getHinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imageviewloaimonan);
        return view;
    }
}
