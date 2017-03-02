package com.jartcorp.hnoss.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jartcorp.hnoss.R;

/**
 * Created by yeh on 15/9/9.
 */
public class AuctingFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "content";
    private View view;
    private ImageView imgHomeHot;
    private String content;

    public static AuctingFragment newInstance(String content) {
        AuctingFragment fragment = new AuctingFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_aucting, container, false);
        init();
        return view;
    }

    private void init() {
        imgHomeHot = (ImageView) view.findViewById(R.id.imgHomeHot);
    }
}


