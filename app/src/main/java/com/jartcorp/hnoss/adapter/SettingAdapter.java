package com.jartcorp.hnoss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.jartcorp.hnoss.R;
import com.jartcorp.hnoss.activity.SettingFragment;

/**
 * Created by asus on 2015/10/13.
 */
public class SettingAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    String []settt;
    /*public SettingAdapter(Context c,String[]settt){
        inflater=LayoutInflater.from(c);
        this.settt=settt;
    }*/

    public SettingAdapter(SettingFragment settingFragment, String[] setttt) {
        inflater=LayoutInflater.from(settingFragment.getActivity());
        this.settt=setttt;
    }


    @Override
    public int getCount() {
        return settt.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        convertView = inflater.inflate(R.layout.seeting_list_tem, viewGroup, false);
        TextView sett;
        sett = (TextView) convertView.findViewById(R.id.settt);
        viewHolder=new ViewHolder();
        viewHolder.sw=(Switch)convertView.findViewById(R.id.switch_item);
        viewHolder.spi=(Spinner)convertView.findViewById(R.id.spinner_item);
        sett.setText(settt[i]);

        if(i==1) {

            viewHolder.sw.setVisibility(convertView.VISIBLE);



            sett.setText(settt[i]);
            convertView.setTag(viewHolder);
        }

        return convertView;

    }

    public final class ViewHolder {
        public Switch sw;
        public Spinner spi;
    }
}
