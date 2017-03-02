package com.jartcorp.hnoss;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;

import android.view.Window;
import android.view.WindowManager;
import android.view.Window;
/**
 * Created by asus on 2015/11/15.
 */
public class GIFWallpaper extends WallpaperService {
    private Context context;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private JSONObject jsonObject;
    private int GIFID;
    private WallpaperManager wallpaperManager;


    @Override
    public Engine onCreateEngine() {
        /*try {
            otherContext=createPackageContext("com.jartcorp.hnoss.activity",CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }*/

        final String url="http://52.76.46.202/g_f";
        context=getApplicationContext();
        requestQueue= Volley.newRequestQueue(context);
        SharedPreferences preferences=context.getSharedPreferences("IDkey",Context.MODE_WORLD_READABLE);
        GIFID=preferences.getInt("ID",0);
        System.out.println(GIFID);
        Log.i("TAG","ffffffffffffffffffffffffffffffffffffffffffffffff");
        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonObject = new JSONObject(response);
                    Object test=jsonObject.getJSONArray("list").getJSONObject(0).get("Album_name");
                    System.out.println(test);
                    Log.i("TAG","I'm GIF Service test");
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


        if (GIFID==1){
            try {
                Movie movie=Movie.decodeStream(getResources().getAssets().open("sakura.gif"));
                return new  GIFWallpaperEngine(movie);
            }catch (IOException e){
                Log.d("GIF","Could not load asset");
                return null;
            }
        }else if (GIFID==2){
            try {
                Movie movie=Movie.decodeStream(getResources().getAssets().open("tree_move.gif"));
                return new  GIFWallpaperEngine(movie);
            }catch (IOException e){
                Log.d("GIF","Could not load asset");
                return null;
            }
        }else if (GIFID==3){
            try {
                Movie movie=Movie.decodeStream(getResources().getAssets().open("kath.gif"));
                return new  GIFWallpaperEngine(movie);
            }catch (IOException e){
                Log.d("GIF","Could not load asset");
                return null;
            }
        }
        return null;
    }



    private class GIFWallpaperEngine extends Engine{
        private final int frameDuration = 20;
        private SurfaceHolder holder;
        private Movie movie;
        private boolean visible;
        private android.os.Handler handler;
        public GIFWallpaperEngine(Movie movie) {
            this.movie = movie;
            handler = new android.os.Handler();

        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            this.holder=surfaceHolder;
            wallpaperManager=WallpaperManager.getInstance(context);
            Log.i("TAG","qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
        }
        private Runnable drawGIF =new Runnable() {
            @Override
            public void run() {
                draw();
            }
        };


        private void draw(){
            if(visible){
                Canvas canvas=holder.lockCanvas();
                canvas.save();
                WindowManager windowManager=(WindowManager)(getSystemService(Context.WINDOW_SERVICE));
                int screenWidth=windowManager.getDefaultDisplay().getWidth();
                int screenHeight=windowManager.getDefaultDisplay().getHeight();
                canvas.scale(screenWidth/720.0f, screenHeight/1009.0f);
                movie.draw(canvas, -0, 0);
                canvas.restore();
                holder.unlockCanvasAndPost(canvas);
                movie.setTime((int) (System.currentTimeMillis() % movie.duration()));
                handler.removeCallbacks(drawGIF);
                handler.postDelayed(drawGIF, frameDuration);


            }
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            this.visible=visible;
            System.out.println(visible);
            Log.i("TAG","tryResult true or false..............................................");
            if(visible){
                handler.post(drawGIF);

            }else {

                handler.removeCallbacks(drawGIF);
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            handler.removeCallbacks(drawGIF);
        }

        @Override
        public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
            System.out.println(xOffset);
            System.out.println(yOffset);
            System.out.println(xOffsetStep);
            System.out.println(yOffsetStep);
            System.out.println(xPixelOffset);//螢幕位置變化值,滑一頁384，往右一頁-384，往左+384
            System.out.println(yPixelOffset);
            Log.i("TAG","wallpaper test move");

            super.onOffsetsChanged(xOffset, yOffset, xOffsetStep, yOffsetStep, xPixelOffset, yPixelOffset);
        }
    }
}