package com.jartcorp.hnoss.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.jartcorp.hnoss.LogingPage;
import com.jartcorp.hnoss.R;
import com.jartcorp.hnoss.adapter.AccountAdapter;
import android.content.SharedPreferences;

/**
 * Created by yeh on 15/9/9.
 */
public class AccountFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "content";
    public static final String r="gogo";
    public  boolean result;
    private View view;
    private ImageView imgHomeHot;
    private String content;
    Integer []accima={R.mipmap.free_small01,R.mipmap.free_small02,R.mipmap.free_small03,R.mipmap.free_small04};
    private GridView gvAccount;


    public static AccountFragment newInstance(String content) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(TAG, content);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        content = bundle != null ? bundle.getString(TAG) : "";
        //result = getArguments().getBoolean(r);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_login, container, false);
        init();
        Button reg = (Button)view.findViewById(R.id.btnRegister);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent();
                intent.setClass(getActivity(), LogingPage.class);
                getActivity().startActivity(intent);*/
            }
        });

        Intent rere=getActivity().getIntent();
        result=rere.getBooleanExtra("res",false);
        //result=getActivity().getSharedPreferences("gogo",0).toString();

        Log.i("TAG","WOWOWOWOWOWOWOWOWOWOW");
        System.out.println(result);
        if (result==true){
            view=inflater.inflate(R.layout.fragment_account,container,false);

        } else if(result==false){
            view = inflater.inflate(R.layout.fragment_account_login, container, false);
        }







        /*gvAccount.setAdapter(new AccountAdapter(AccountFragment.this, accima));

        gvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),DetailActivity.class);
                getActivity().startActivity(intent);
            }
        });*/
        return view;
    }

    private void init() {
        //imgHomeHot = (ImageView) view.findViewById(R.id.imgHomeHot);
        //gvAccount = (GridView)view.findViewById(R.id.gvAccount);


    }






}

