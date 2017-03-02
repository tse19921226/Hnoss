package com.jartcorp.hnoss.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.games.request.GameRequest;
import com.jartcorp.hnoss.R;
import com.jartcorp.hnoss.activity.GalleryFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by asus on 2015/12/2.
 */
public class GallerySpinnerAdapter extends BaseAdapter {
    public LayoutInflater inflaterSpi;
    public String [] gvSpinner;
    public Context gvcontext;
    public RequestQueue grequestQueue;
    public StringRequest gstringRequest;
    public JSONObject jsonObjectAuthor;
    public TextView spiText;
    public  int getposition;


    public GallerySpinnerAdapter(GalleryFragment galleryFragment,String [] gvSpinneritem){
        inflaterSpi=LayoutInflater.from(galleryFragment.getActivity());
        this.gvSpinner=gvSpinneritem;
    }
    @Override
    public int getCount() {

        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        convertView=inflaterSpi.inflate(R.layout.gallery_spinner,parent,false);
        spiText=(TextView)convertView.findViewById(R.id.gallerySpiText);
        spiText.setText(gvSpinner[position]);
        /*getposition=position;
        final  String urlAutor="http://52.76.46.202/g_au";
        gvcontext = convertView.getContext();
        grequestQueue= Volley.newRequestQueue(gvcontext);
        gstringRequest=new StringRequest(com.android.volley.Request.Method.POST, urlAutor, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonObjectAuthor=new JSONObject(response);
                    Object getAuthor01=jsonObjectAuthor.getJSONArray("list").getJSONObject(position).get("name");
                    Object getAuthor02=jsonObjectAuthor.getJSONArray("list").getJSONObject(position).get("name");
                    //gvSpinner=new String[]{"ALL", String.valueOf(getAuthor01),String.valueOf(getAuthor01)};
                    System.out.println(gvSpinner);
                    System.out.println(position);
                    System.out.println(getposition);
                    Log.i("TAG", "1919191919919919191999199999919");
                    gvSpinner=String.valueOf(getAuthor01);
                    spiText.setText(gvSpinner);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            protected HashMap<String,String> getParams()throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<String,String>();
                hashMap.put("s_token", "7H2IIlsaE5zU2afTsiOA0G7Yh3vvnCPaT7qpTK8e0ei4jNLUp9SdnwtXPDVBQG271paZs1b9Jfo6YiEOJSjI7fdPK5HAGCCXxAiq");
                return hashMap;
            }
        };
        grequestQueue.add(gstringRequest);*/

        return convertView;
    }
}
