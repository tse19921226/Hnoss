package com.jartcorp.hnoss.activity;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jartcorp.hnoss.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by yeh on 15/9/9.
 */
public class FreeFragment extends Fragment {

    public static final String TAG = "content";
    private View view;
    private ImageView imgFree;
    private ImageView imgFreeone;
    private ImageView imgFreeTwo;
    private ImageView imgFreeThree;
    private ImageView imgFreeFour;
    private String content;
    private Context context;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private JSONObject jsonObject;

    public static FreeFragment newInstance(String content) {
        FreeFragment fragment = new FreeFragment();
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
        view = inflater.inflate(R.layout.fragment_free, container, false);
        init();
        setClick();
        return view;
    }

    private void setClick() {
        imgFreeone.setOnClickListener(onClickListener);
        imgFreeTwo.setOnClickListener(onClickListener);
        imgFreeThree.setOnClickListener(onClickListener);
        imgFreeFour.setOnClickListener(onClickListener);
        imgFree.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imgFreeOne:
                    changeImage(v);
                    break;
                case R.id.imgFreeTwo:
                    changeImage(v);
                    break;
                case R.id.imgFreeThree:
                    changeImage(v);
                    break;
                case R.id.imgFreeFour:
                    changeImage(v);
                    break;
                case R.id.imgFree:
                    if (imgFree.getDrawable() != null) {
                        final String url = "http://52.76.46.202/g_f";

                        context = getActivity().getApplicationContext();
                        requestQueue = Volley.newRequestQueue(context);
                        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    jsonObject = new JSONObject(response);
                                    int tag = (Integer) imgFree.getTag();
                                    Object j_year = jsonObject.getJSONArray("list").getJSONObject(tag).get("year");
                                    Object j_material = jsonObject.getJSONArray("list").getJSONObject(tag).get("material");
                                    Object j_Album_name = jsonObject.getJSONArray("list").getJSONObject(tag).get("Album_name");
                                    Object j_author = jsonObject.getJSONArray("list").getJSONObject(tag).get("author");
                                    Object j_about = jsonObject.getJSONArray("list").getJSONObject(tag).get("about");
                                    Object j_about_eng = jsonObject.getJSONArray("list").getJSONObject(tag).get("about_eng");
                                    Object j_download = jsonObject.getJSONArray("list").getJSONObject(tag).get("download");
                                    Object j_imaUrl = jsonObject.getJSONArray("list").getJSONObject(tag).getJSONArray("list").get(0);
                                    Object j_size = jsonObject.getJSONArray("list").getJSONObject(tag).get("size");
                                    Object jsonID = jsonObject.getJSONArray("list").getJSONObject(tag).get("ab_id");
                                    String year = String.valueOf(j_year);
                                    String material = String.valueOf(j_material);
                                    String Album_name = String.valueOf(j_Album_name);
                                    String author = String.valueOf(j_author);
                                    String about = String.valueOf(j_about);
                                    String about_eng = String.valueOf(j_about_eng);
                                    String download = String.valueOf(j_download);
                                    String imaUrl = "http://52.76.46.202/"+String.valueOf(j_imaUrl);
                                    String size = String.valueOf(j_size);
                                    String  ab_id= String.valueOf(jsonID);
                                    Intent intent = new Intent();
                                    intent.setClass(getActivity(), DetailActivity.class);
                                    intent.putExtra("year", year);
                                    intent.putExtra("material", material);
                                    intent.putExtra("Album_name", Album_name);
                                    intent.putExtra("author", author);
                                    intent.putExtra("about", about);
                                    intent.putExtra("about_eng", about_eng);
                                    intent.putExtra("download", download);
                                    intent.putExtra("ima",imaUrl);
                                    intent.putExtra("id",ab_id);
                                    intent.putExtra("size",size);
                                    getActivity().startActivity(intent);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println("請求錯誤:" + error.toString());
                                Log.i("TAG", "====================");
                            }
                        }) {
                            @Override
                            protected HashMap<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> hashMap = new HashMap<String, String>();
                                hashMap.put("s_token", "7H2IIlsaE5zU2afTsiOA0G7Yh3vvnCPaT7qpTK8e0ei4jNLUp9SdnwtXPDVBQG271paZs1b9Jfo6YiEOJSjI7fdPK5HAGCCXxAiq");
                                return hashMap;
                            }
                        };
                        requestQueue.add(stringRequest);
                    }
                    break;
            }
        }
    };


    public void goToAttract(View v)
    {
        Intent intent = new Intent();
        intent.setClass(getActivity(), DetailActivity.class);
        getActivity().startActivity(intent);
    }

    private void changeImage(View v) {
        switch (v.getId()){
            case R.id.imgFreeOne:
                //imgFree.setBackgroundDrawable(imgFreeone.getBackground());
                imgFree.setImageDrawable(imgFreeone.getDrawable());
                imgFree.setTag(2);
                break;
            case R.id.imgFreeTwo:
                imgFree.setImageDrawable(imgFreeTwo.getDrawable());
                imgFree.setTag(0);
                //Toast.makeText(view.getContext(), "imgFreeTwo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgFreeThree:
                imgFree.setImageDrawable(imgFreeThree.getDrawable());
                imgFree.setTag(1);
                //Toast.makeText(view.getContext(), "imgFreeThree", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgFreeFour:
                imgFree.setImageDrawable(imgFreeFour.getDrawable());
                imgFree.setTag(3);
                //Toast.makeText(view.getContext(), "imgFreeFour", Toast.LENGTH_SHORT).show();
                break;
        }
    }




    private void init() {
        imgFree = (ImageView) view.findViewById(R.id.imgFree);
        imgFreeone = (ImageView) view.findViewById(R.id.imgFreeOne);
        imgFreeTwo = (ImageView) view.findViewById(R.id.imgFreeTwo);
        imgFreeThree = (ImageView) view.findViewById(R.id.imgFreeThree);
        imgFreeFour = (ImageView) view.findViewById(R.id.imgFreeFour);
        imgFreeone.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }
}
