package com.jartcorp.hnoss.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.jartcorp.hnoss.R;
import com.jartcorp.hnoss.activity.AccountFragment;

/**
 * Created by asus on 2015/10/15.
 */
public class AccountAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    Integer []accima;

    public AccountAdapter(AccountFragment accountFragment,Integer[]accima){
        inflater=LayoutInflater.from(accountFragment.getActivity());
        this.accima=accima;

    }
    @Override
    public int getCount() {
        return accima.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.account_item,parent,false);
        ImageView imageView;
        imageView=(ImageView)convertView.findViewById(R.id.accountIG);
        imageView.setImageResource(accima[position]);


        return convertView;

    }
}
