package com.jartcorp.hnoss;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jartcorp.hnoss.activity.DetailActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Tse_Chen on 2016/10/14.
 */

public class JsonActivity {

    public void jsonData(String url, Context context, final int tag,final Intent intent){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
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

}
