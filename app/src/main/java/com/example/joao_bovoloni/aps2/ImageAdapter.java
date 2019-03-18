package com.example.joao_bovoloni.aps2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;


public class ImageAdapter extends BaseAdapter {
    private Context context;
    private  int[] mobileValues;
    private LayoutInflater inflater;


    public ImageAdapter(Context context, int[] mobileValues) {


        this.context = context;
        this.mobileValues = mobileValues;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.adapter_gridview, parent, false);
        }
        final int min = 20;
        final int max = 80;
        final int random = new Random().nextInt((max - min) + 1) + min;
        ImageView imageView = (ImageView) convertView
                .findViewById(R.id.grid_item_image);
        TextView textView = (TextView) convertView
                .findViewById(R.id.grid_item_label);
        textView.setText(String.valueOf(random));
        imageView.setImageResource(mobileValues[position]);

//        Picasso.get().load(mobileValues[position]).into(imageView);
        return convertView;
    }
    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}