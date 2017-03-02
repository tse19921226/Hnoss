package com.jartcorp.hnoss.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Application;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Movie;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.service.wallpaper.WallpaperService;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jartcorp.hnoss.GIFWallpaper;
import com.jartcorp.hnoss.MainActivity;
import com.jartcorp.hnoss.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by yeh on 15/9/19.
 */
public class DetailActivity extends AppCompatActivity {

    private ImageView imgMenuTouch;
    private ImageView imgDetail;
    private TextView tvDetailAuthor_write;
    private TextView tvDetailTitle_write;
    private TextView tvDetailAge_write;
    private TextView tvDetailMaterial_write;
    private TextView tvDetailSize_write;
    private TextView tvDetailDownload_write;
    private Button downPaper;
    private Movie movie;
    private URL imaUrl;
    private HttpURLConnection conn;
    private InputStream input;
    private Bitmap getIma;
    private String ima;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public String id;
    public int imageID;
    public WallpaperManager wallpaperManager;
    public Context context;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private JSONObject jsonObject;
    private int download_point;
    private int arrayID;
    public DisplayImageOptions displayImageOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        sharedPreferences=this.getSharedPreferences("IDkey",MODE_WORLD_WRITEABLE);
        editor=sharedPreferences.edit();
        init();
        setClick();
        final Intent intent=getIntent();
        String year=intent.getStringExtra("year");
        String material=intent.getStringExtra("material");
        String Album_name=intent.getStringExtra("Album_name");
        String author=intent.getStringExtra("author");
        String about=intent.getStringExtra("about");
        String about_eng=intent.getStringExtra("about_eng");
        final String download=intent.getStringExtra("download");

        String size=intent.getStringExtra("size");
        ima=intent.getStringExtra("ima");
        id=intent.getStringExtra("id");
        //System.out.println(id);
        imageID=Integer.parseInt(id);
        //System.out.println(imageID);
        arrayID=intent.getIntExtra("array_id",200);
        //Log.i("TAG", "RFRFGREGREGWFWRGGWRGWGRGWREGERGw");


        urlImage();



        tvDetailAge_write.setText(year);
        tvDetailMaterial_write.setText(material);
        tvDetailTitle_write.setText(Album_name+"\n"+about+"\n"+about_eng);
        tvDetailAuthor_write.setText(author);
        tvDetailSize_write.setText(size);
        tvDetailDownload_write.setText(download);
        context=getApplicationContext();
        wallpaperManager=WallpaperManager.getInstance(context);
        downPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wallpaperManager.getDrawable() == null) {
                    System.out.println(wallpaperManager.getDrawable());
                    Log.i("TAG", "test has Wallpaper?..............................................................");
                    final String url = "http://52.76.46.202/d_a";
                    requestQueue = Volley.newRequestQueue(context);
                    stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println(response);
                            try {
                                jsonObject = new JSONObject(response);
                                System.out.println(response);
                                Object jsonOBJ = jsonObject.get("download");
                                String s = String.valueOf(jsonOBJ);
                                tvDetailDownload_write.setText(s);
                                System.out.println(s);
                                Log.i("TAG","testDownload...............................................................................");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("請求錯誤:" + error.toString());
                        }
                    }) {
                        @Override
                        protected HashMap<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("ab_id",id);
                            hashMap.put("s_token", "7H2IIlsaE5zU2afTsiOA0G7Yh3vvnCPaT7qpTK8e0ei4jNLUp9SdnwtXPDVBQG271paZs1b9Jfo6YiEOJSjI7fdPK5HAGCCXxAiq");
                            hashMap.put("c_token","G6m3dDBlTIZb07TVFT71x5MF9aGjxC2eIohW2ThVCh7Co0y3UG5rLR6V2NezpgO8F55HZmCBEJe2KN5Etb5f2ba5ZpEoFtxlyC3y");
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);

                    openService();
                } else {
                        //try {
                        //wallpaperManager.clear();
                        System.out.println(RESULT_OK);
                        Log.i("TAG", "clear clear.........................................................");
                        final String url = "http://52.76.46.202/d_a";
                        requestQueue = Volley.newRequestQueue(context);
                        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println(response);
                                try {
                                    jsonObject = new JSONObject(response);
                                    System.out.println(response);
                                    Object jsonOBJ = jsonObject.get("download");
                                    String s = String.valueOf(jsonOBJ);
                                    tvDetailDownload_write.setText(s);
                                    System.out.println(s);
                                    Log.i("TAG", "testDownload...............................................................................");
                                } catch (JSONException e) {
                                    Log.i("TAG", "error.......................................................................................");
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("TAG", "error.......................................................................................");
                                System.out.println("請求錯誤:" + error.toString());
                            }
                        }) {
                            @Override
                            protected HashMap<String, String> getParams() throws AuthFailureError {
                                HashMap<String, String> hashMap = new HashMap<String, String>();
                                hashMap.put("ab_id", id);
                                hashMap.put("s_token", "7H2IIlsaE5zU2afTsiOA0G7Yh3vvnCPaT7qpTK8e0ei4jNLUp9SdnwtXPDVBQG271paZs1b9Jfo6YiEOJSjI7fdPK5HAGCCXxAiq");
                                hashMap.put("c_token", "G6m3dDBlTIZb07TVFT71x5MF9aGjxC2eIohW2ThVCh7Co0y3UG5rLR6V2NezpgO8F55HZmCBEJe2KN5Etb5f2ba5ZpEoFtxlyC3y");
                                return hashMap;
                            }
                        };
                        requestQueue.add(stringRequest);
                        v.postInvalidate();
                        openService();
                    /*} catch (IOException e) {
                        e.printStackTrace();
                        }*/

                }
            }
        });
    }



    public void openService() {

        Intent paper = new Intent(wallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        paper.putExtra(wallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(DetailActivity.this, GIFWallpaper.class));
        editor.putInt("ID", imageID);
        editor.apply();
        //System.out.println(imageID);
        //Log.i("TAG", "SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
        startActivity(paper);
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK){
            try {
                wallpaperManager.clear();

            } catch (IOException e) {
                e.printStackTrace();
            }


            }else {

        }
        System.out.println(resultCode);
        Log.i("TAG","tryResult true or false..............................................");
      return;
    }*/

    public void urlImage (){

        ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
        displayImageOptions=new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.loading_icon).showImageOnFail(R.drawable.error_icon)
                .cacheInMemory(true).cacheOnDisk(true).build();
        ImageLoader.getInstance().displayImage(ima,imgDetail,displayImageOptions);


    }



    private void setClick() {
        imgMenuTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void init() {
        imgMenuTouch = (ImageView)findViewById(R.id.imgMenuTouch);
        imgDetail = (ImageView)findViewById(R.id.imgDetail);
        tvDetailAuthor_write = (TextView)findViewById(R.id.tvDetailAuthor_write);
        tvDetailTitle_write = (TextView)findViewById(R.id.tvDetailTitle_write);
        tvDetailAge_write = (TextView)findViewById(R.id.tvDetailAge_write);
        tvDetailSize_write = (TextView)findViewById(R.id.tvDetailSize_write);
        tvDetailMaterial_write=(TextView)findViewById(R.id.tvDetailMaterial_write);
        tvDetailDownload_write=(TextView)findViewById(R.id.tvDetailDownload_write);
        downPaper=(Button)findViewById(R.id.tvDetailDownloadWallpaper);

    }


}

