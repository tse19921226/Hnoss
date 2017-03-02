package com.jartcorp.hnoss.adapter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.jartcorp.hnoss.R;
import com.jartcorp.hnoss.activity.GalleryFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by yeh on 15/9/18.
 */
public class GalleryAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    Integer []ima;
    String albumName,Author;
    Context context;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    JSONObject json;

public GalleryAdapter(GalleryFragment galleryFragment, Integer[] ima, String album_name, String author){
    inflater=LayoutInflater.from(galleryFragment.getActivity());
    this.ima=ima;
    this.albumName=album_name;
    this.Author=author;

}


    @Override
    public int getCount() {
        return ima.length;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView=inflater.inflate(R.layout.gallery_item,parent,false);
        ImageView imageView;
        final TextView author,album_name;
        imageView=(ImageView)convertView.findViewById(R.id.galleryIG);
        album_name=(TextView)convertView.findViewById(R.id.gallery_albunName);
        author=(TextView)convertView.findViewById(R.id.gallery_author);
        imageView.setImageResource(ima[position]);

        final String url = "http://52.76.46.202/g_h";
        context=convertView.getContext();
        requestQueue = Volley.newRequestQueue(context);
        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    json=new JSONObject(response);
                    Object getAlbumname=json.getJSONArray("list").getJSONObject(position).get("Album_name");
                    Object getAuthor=json.getJSONArray("list").getJSONObject(position).get("author");
                    albumName= String.valueOf(getAlbumname);
                    album_name.setText(albumName);
                    Author=String.valueOf(getAuthor);
                    author.setText(Author);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected HashMap<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("s_token", "7H2IIlsaE5zU2afTsiOA0G7Yh3vvnCPaT7qpTK8e0ei4jNLUp9SdnwtXPDVBQG271paZs1b9Jfo6YiEOJSjI7fdPK5HAGCCXxAiq");
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);

        return convertView;
    }




}

