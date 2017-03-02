package com.jartcorp.hnoss.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by yeh on 15/9/17.
 */
public class HomeImageAdapter extends BaseAdapter {
    private Context mContext;
    private Integer width;
    private Integer height;
    private AnimationDrawable[] anims;

    public HomeImageAdapter(Context context) {
        mContext = context;
    }


    @Override
    public int getCount() {
        return anims.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(mContext);
        imageView.setImageDrawable(anims[position]);
        imageView.setLayoutParams(new Gallery.LayoutParams(width,height));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        return imageView;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public AnimationDrawable[] getmImageIds() {
        return anims;
    }

    public void setmImageAnim(AnimationDrawable[] anims) {
        this.anims = anims;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

}

