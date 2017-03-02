package com.jartcorp.hnoss.activity;


import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.Request;
import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.jartcorp.hnoss.R;
import com.jartcorp.hnoss.adapter.GalleryAdapter;
import com.jartcorp.hnoss.adapter.GallerySpinnerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.transform.ErrorListener;

/**
 * Created by yeh on 15/9/9.
 */
public class GalleryFragment extends Fragment {

    public static final String TAG = "content";
    private View view;
    private String content;
    private GridView gvGallery;
    private Spinner gvSpinner;
    private Context gcontext;
    private RequestQueue grequestQueue;
    private StringRequest gstringRequest;
    private Context context;
    private Context gvcontext;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private JSONObject jsonObject;
    private JSONObject jsonObjectAuthor;
    public String [] gvSpinneritem;
    public String album_name,author;

    private com.nostra13.universalimageloader.core.ImageLoader imageLoader;

    Integer []ima={R.mipmap.sakura030000,R.mipmap.kath0000,R.mipmap.treemove020000};

    public static GalleryFragment newInstance(String content) {
        GalleryFragment fragment = new GalleryFragment();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gallery, container, false);
        init();
        final  String urlAutor="http://52.76.46.202/g_au";
        gvcontext = getActivity().getApplicationContext();
        grequestQueue= Volley.newRequestQueue(gvcontext);
        gstringRequest=new StringRequest(com.android.volley.Request.Method.POST, urlAutor, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonObjectAuthor=new JSONObject(response);
                    Object getAuthor01=jsonObjectAuthor.getJSONArray("list").getJSONObject(0).get("name");
                    Object getAuthor02=jsonObjectAuthor.getJSONArray("list").getJSONObject(1).get("name");
                    gvSpinneritem= new String[]{"ALL", String.valueOf(getAuthor01), String.valueOf(getAuthor02)};
                    gvSpinner.setAdapter(new GallerySpinnerAdapter(GalleryFragment.this,gvSpinneritem));
                    System.out.print(gvSpinneritem);
                    Log.i("TAG","1919191919919919191999199999919");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected HashMap<String,String> getParams()throws AuthFailureError{
                HashMap<String,String> hashMap=new HashMap<String,String>();
                hashMap.put("s_token", "7H2IIlsaE5zU2afTsiOA0G7Yh3vvnCPaT7qpTK8e0ei4jNLUp9SdnwtXPDVBQG271paZs1b9Jfo6YiEOJSjI7fdPK5HAGCCXxAiq");
                return hashMap;
            }
        };
        grequestQueue.add(gstringRequest);
        //gvSpinner.setAdapter(new GallerySpinnerAdapter(GalleryFragment.this, gvSpinneritem));
        gvSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        gvGallery.setAdapter(new GalleryAdapter(GalleryFragment.this, ima,album_name,author));
        gvGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                final String url = "http://52.76.46.202/g_h";
                context = getActivity().getApplicationContext();
                requestQueue = Volley.newRequestQueue(context);
                stringRequest = new StringRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Log.i("TAG", "++++++++++++++++++++");

                        try {
                            jsonObject = new JSONObject(response);
                            Object jsonOBJ = jsonObject.getJSONArray("list").getJSONObject(position).get("year");
                            Object jsonOBJ2 = jsonObject.getJSONArray("list").getJSONObject(position).get("material");
                            Object jsonOBJ3 = jsonObject.getJSONArray("list").getJSONObject(position).get("Album_name");
                            Object jsonOBJ4 = jsonObject.getJSONArray("list").getJSONObject(position).get("author");
                            Object jsonOBJ5 = jsonObject.getJSONArray("list").getJSONObject(position).get("about");
                            Object jsonOBJ6 = jsonObject.getJSONArray("list").getJSONObject(position).get("about_eng");
                            Object jsonOBJ7 = jsonObject.getJSONArray("list").getJSONObject(position).get("download");
                            Object jsonOBJ8 = jsonObject.getJSONArray("list").getJSONObject(position).getJSONArray("list").get(0);
                            Object jsonID=jsonObject.getJSONArray("list").getJSONObject(position).get("ab_id");
                            //System.out.println(jsonOBJ);
                            Log.i("TAG", "************************");
                            HashMap<String, Object> hashMap = new HashMap<String, Object>();
                            hashMap.put("key01", jsonOBJ);
                            hashMap.put("key02", jsonOBJ2);
                            System.out.println(hashMap);
                            Log.i("TAG", "////////////////////////////////");
                            String s = String.valueOf(jsonOBJ);
                            String s1 = String.valueOf(jsonOBJ2);
                            String s2 = String.valueOf(jsonOBJ3);
                            String s3 = String.valueOf(jsonOBJ4);
                            String s4 = String.valueOf(jsonOBJ5);
                            String s5 = String.valueOf(jsonOBJ6);
                            String s6 = String.valueOf(jsonOBJ7);
                            String s7 = "http://52.76.46.202/"+String.valueOf(jsonOBJ8);
                            String  ab_id= String.valueOf(jsonID);
                            System.out.println(s);
                            Log.i("TAG", "8888888888888888888888888888");
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), DetailActivity.class);
                            intent.putExtra("year", s);
                            intent.putExtra("material", s1);
                            intent.putExtra("Album_name", s2);
                            intent.putExtra("author", s3);
                            intent.putExtra("about", s4);
                            intent.putExtra("about_eng", s5);
                            intent.putExtra("download", s6);
                            intent.putExtra("ima",s7);
                            intent.putExtra("id",ab_id);
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
        });


        return view;
    }

    private void init() {
        gvGallery = (GridView)view.findViewById(R.id.gvGallery);
        gvSpinner=(Spinner)view.findViewById(R.id.spAuthor);
    }




}
