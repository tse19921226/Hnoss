package com.jartcorp.hnoss.activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jartcorp.hnoss.R;
import com.jartcorp.hnoss.adapter.GalleryAdapter;
import com.jartcorp.hnoss.adapter.SettingAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by yeh on 15/9/9.
 */
public class SettingFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "content";
    private View view;
    private ImageView imgHomeHot;
    private String content;
    private ListView listV;
    public String [] setttt;
    private String ann,pro,noti,la,ve;
    public String tw,china,us,es;


    public static SettingFragment newInstance(String content) {
        SettingFragment fragment = new SettingFragment();
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        init();

        final NotificationManager notificationManager=(NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder nb=new Notification.Builder(getActivity());
        nb.setWhen(System.currentTimeMillis());
        nb.setContentTitle("JART");
        nb.setContentText("Test");
        nb.setSmallIcon(R.mipmap.ic_launcher);
        nb.setDefaults(Notification.DEFAULT_VIBRATE);
        nb.setAutoCancel(true);
        final Notification notification=nb.build();
        notification.flags=Notification.FLAG_AUTO_CANCEL;

        ann= getText(R.string.announcement).toString();
        pro=getText(R.string.provision).toString();
        noti=getText(R.string.notification).toString();
        la=getText(R.string.languages).toString();
        ve=getText(R.string.version).toString();

        tw=getText(R.string.chinese_Traditional).toString();
        china=getText(R.string.chinese).toString();
        us=getText(R.string.english).toString();
        es=getText(R.string.español).toString();

        setttt= new String[]{ann,noti,pro,la,ve};
        listV.setAdapter(new SettingAdapter(SettingFragment.this, setttt));
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    new AlertDialog.Builder(getActivity()).setTitle(getText(R.string.announcement))
                            .setMessage(getText(R.string.announcement_write)).setPositiveButton(getText(R.string.enter),null).show();
                }
                if (position==1){
                    notificationManager.notify(1,notification);



                }
                if (position==2){
                    new AlertDialog.Builder(getActivity()).setTitle(getText(R.string.provision))
                            .setMessage(getText(R.string.provision_write)).setPositiveButton(getText(R.string.enter),null).show();
                }
                if (position==3){
                    if (getResources().getConfiguration().locale.getCountry().equals("CN")){
                        new AlertDialog.Builder(getActivity()).setTitle("")
                                .setMessage("请于手机设定中更改语言"+"\n"+tw+","+china+","+us+","+es).setPositiveButton("确定", null).show();
                    }else if (getResources().getConfiguration().locale.getCountry().equals("TW")){
                        new AlertDialog.Builder(getActivity()).setTitle("")
                                .setMessage("請於手機設定中更改語言"+"\n"+tw+","+china+","+us+","+es).setPositiveButton("確定",null).show();
                    }else if (getResources().getConfiguration().locale.getCountry().equals("US")){
                        new AlertDialog.Builder(getActivity()).setTitle("")
                                .setMessage("Please change the language in your phone settings"+"\n"+tw+","+china+","+us+","+es).setPositiveButton("OK",null).show();
                    }else if (getResources().getConfiguration().locale.getCountry().equals("ES")){
                        new AlertDialog.Builder(getActivity()).setTitle("")
                                .setMessage("Por favor, cambiar el idioma en la configuración del teléfono"+"\n"+tw+","+china+","+us+","+es).setPositiveButton("determinar",null).show();
                    }


                }
                if (position==4){
                    new AlertDialog.Builder(getActivity()).setTitle("")
                            .setMessage("Hnoss1.1").setPositiveButton(getText(R.string.enter),null).show();
                }
            }
        });

        return view;
    }

    private void init() {
        imgHomeHot = (ImageView) view.findViewById(R.id.imgHomeHot);
        listV=(ListView)view.findViewById(R.id.lvSetting);





    }
}
