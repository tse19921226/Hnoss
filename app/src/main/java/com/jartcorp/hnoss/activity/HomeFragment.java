package com.jartcorp.hnoss.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jartcorp.hnoss.R;
import com.jartcorp.hnoss.adapter.HomeImageAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by yeh on 15/9/9.
 */
public class HomeFragment extends Fragment {

    public static final String TAG = "content";
    private View view;
    private ImageView imgHomeHot;
    private String content;
    private Gallery glImageHot;
    private HomeImageAdapter homeImageAdapter;
    private AnimationDrawable frameAnim;
    private AnimationDrawable[] anims;
    private int animNumber;
    private ViewFlipper view_Flipper;
    private Context context;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private JSONObject jsonObject;
    private ImageView free01,free02,free03,free04,btnR,btnL;
    private GestureDetector gestureDetector = null;
    private MotionEvent e1, e2;
    public final String url = "http://52.76.46.202/g_f";



    public static HomeFragment newInstance(String content) {
        HomeFragment fragment = new HomeFragment();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        init();

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_Flipper.showNext();
            }
        });
        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_Flipper.showPrevious();
            }
        });

        view_Flipper.isFlipping();




        free01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final String url = "http://52.76.46.202/g_f";
                context=getActivity().getApplicationContext();
                requestQueue= Volley.newRequestQueue(context);
                stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            jsonObject = new JSONObject(response);


                            Object J_year=jsonObject.getJSONArray("list").getJSONObject(2).get("year");
                            Object J_material=jsonObject.getJSONArray("list").getJSONObject(2).get("material");
                            Object J_Album_name=jsonObject.getJSONArray("list").getJSONObject(2).get("Album_name");
                            Object J_author=jsonObject.getJSONArray("list").getJSONObject(2).get("author");
                            Object J_about=jsonObject.getJSONArray("list").getJSONObject(2).get("about");
                            Object J_about_eng=jsonObject.getJSONArray("list").getJSONObject(2).get("about_eng");
                            Object J_download=jsonObject.getJSONArray("list").getJSONObject(2).get("download");
                            Object J_size=jsonObject.getJSONArray("list").getJSONObject(2).get("size");
                            Object J_imaUrl=jsonObject.getJSONArray("list").getJSONObject(2).getJSONArray("list").get(0);
                            Object jsonID=jsonObject.getJSONArray("list").getJSONObject(2).get("ab_id");
                            String year=String.valueOf(J_year);
                            String material=String.valueOf(J_material);
                            String Album_name=String.valueOf(J_Album_name);
                            String author=String.valueOf(J_author);
                            String about=String.valueOf(J_about);
                            String about_eng=String.valueOf(J_about_eng);
                            String download=String.valueOf(J_download);
                            String size=String.valueOf(J_size);
                            String imaUrl="http://52.76.46.202/"+String.valueOf(J_imaUrl);
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
                            intent.putExtra("size",size);
                            intent.putExtra("ima",imaUrl);
                            intent.putExtra("id", ab_id);
                            intent.putExtra("array_id", 0);
                            getActivity().startActivity(intent);
                            onPause();


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
                }){
                    @Override
                    protected HashMap<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap=new HashMap<String, String>();
                        hashMap.put("s_token", "7H2IIlsaE5zU2afTsiOA0G7Yh3vvnCPaT7qpTK8e0ei4jNLUp9SdnwtXPDVBQG271paZs1b9Jfo6YiEOJSjI7fdPK5HAGCCXxAiq");
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);

            }
        });

        free02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final String url = "http://52.76.46.202/g_f";
                context = getActivity().getApplicationContext();
                requestQueue = Volley.newRequestQueue(context);
                stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            jsonObject = new JSONObject(response);
                            Object J_year = jsonObject.getJSONArray("list").getJSONObject(0).get("year");
                            Object J_material = jsonObject.getJSONArray("list").getJSONObject(0).get("material");
                            Object J_Album_name = jsonObject.getJSONArray("list").getJSONObject(0).get("Album_name");
                            Object J_author = jsonObject.getJSONArray("list").getJSONObject(0).get("author");
                            Object J_about = jsonObject.getJSONArray("list").getJSONObject(0).get("about");
                            Object J_about_eng = jsonObject.getJSONArray("list").getJSONObject(0).get("about_eng");
                            Object J_download = jsonObject.getJSONArray("list").getJSONObject(0).get("download");
                            Object J_size = jsonObject.getJSONArray("list").getJSONObject(0).get("size");
                            Object J_imaUrl = jsonObject.getJSONArray("list").getJSONObject(0).getJSONArray("list").get(0);
                            Object jsonID = jsonObject.getJSONArray("list").getJSONObject(0).get("ab_id");
                            String year = String.valueOf(J_year);
                            String material = String.valueOf(J_material);
                            String Album_name = String.valueOf(J_Album_name);
                            String author = String.valueOf(J_author);
                            String about = String.valueOf(J_about);
                            String about_eng = String.valueOf(J_about_eng);
                            String download = String.valueOf(J_download);
                            String size = String.valueOf(J_size);
                            String imaUrl = "http://52.76.46.202/" + String.valueOf(J_imaUrl);
                            String ab_id = String.valueOf(jsonID);
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), DetailActivity.class);
                            intent.putExtra("year", year);
                            intent.putExtra("material", material);
                            intent.putExtra("Album_name", Album_name);
                            intent.putExtra("author", author);
                            intent.putExtra("about", about);
                            intent.putExtra("about_eng", about_eng);
                            intent.putExtra("download", download);
                            intent.putExtra("size", size);
                            intent.putExtra("ima", imaUrl);
                            intent.putExtra("id", ab_id);
                            intent.putExtra("array_id", 1);
                            getActivity().startActivity(intent);
                            onPause();



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

        free03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final String url = "http://52.76.46.202/g_f";
                context = getActivity().getApplicationContext();
                requestQueue = Volley.newRequestQueue(context);
                stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            jsonObject = new JSONObject(response);
                            Object J_year = jsonObject.getJSONArray("list").getJSONObject(1).get("year");
                            Object J_material = jsonObject.getJSONArray("list").getJSONObject(1).get("material");
                            Object J_Album_name = jsonObject.getJSONArray("list").getJSONObject(1).get("Album_name");
                            Object J_author = jsonObject.getJSONArray("list").getJSONObject(1).get("author");
                            Object J_about = jsonObject.getJSONArray("list").getJSONObject(1).get("about");
                            Object J_about_eng = jsonObject.getJSONArray("list").getJSONObject(1).get("about_eng");
                            Object J_download = jsonObject.getJSONArray("list").getJSONObject(1).get("download");
                            Object J_imaUrl = jsonObject.getJSONArray("list").getJSONObject(1).getJSONArray("list").get(0);
                            Object J_size = jsonObject.getJSONArray("list").getJSONObject(1).get("size");
                            Object jsonID = jsonObject.getJSONArray("list").getJSONObject(1).get("ab_id");
                            String year = String.valueOf(J_year);
                            String material = String.valueOf(J_material);
                            String Album_name = String.valueOf(J_Album_name);
                            String author = String.valueOf(J_author);
                            String about = String.valueOf(J_about);
                            String about_eng = String.valueOf(J_about_eng);
                            String download = String.valueOf(J_download);
                            String imaUrl = "http://52.76.46.202/" + String.valueOf(J_imaUrl);
                            String size = String.valueOf(J_size);
                            String ab_id = String.valueOf(jsonID);
                            Intent intent = new Intent();
                            intent.setClass(getActivity(), DetailActivity.class);
                            intent.putExtra("year", year);
                            intent.putExtra("material", material);
                            intent.putExtra("Album_name", Album_name);
                            intent.putExtra("author", author);
                            intent.putExtra("about", about);
                            intent.putExtra("about_eng", about_eng);
                            intent.putExtra("download", download);
                            intent.putExtra("ima", imaUrl);
                            intent.putExtra("id", ab_id);
                            intent.putExtra("size",size);
                            intent.putExtra("array_id", 2);
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


    /*public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
        if (e2.getX() - e1.getX() > 120) {            // 从左向右滑动（左进右出）



            view_Flipper.showPrevious();
            return true;
        } else if (e2.getX() - e1.getX() < -120) {        // 从右向左滑动（右进左出）



            view_Flipper.showNext();
            return true;
        }

        return true;
    }*/

    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init() {

        view_Flipper = (ViewFlipper)view.findViewById(R.id.viewFlipper);
        homeImageAdapter = new HomeImageAdapter(view.getContext());
        free01=(ImageView)view.findViewById(R.id.imageFree01);
        free02=(ImageView)view.findViewById(R.id.imageFree02);
        free03=(ImageView)view.findViewById(R.id.imageFree03);
        btnR=(ImageView)view.findViewById(R.id.btnRight);
        btnL=(ImageView)view.findViewById(R.id.btnLeft);
        animNumber = 0;
        view_Flipper.isAutoStart();
        view_Flipper.setFlipInterval(7000);
        view_Flipper.startFlipping();

    }






     @Override
    public void onStart() {
        super.onStart();



    }

   /* private void loadImage() {

        try{
            for (animNumber = 0;animNumber < 1 ;animNumber++){
                for (int i = 0; i <= 59; i++) {
                    Log.d("Home","do?");
                    String str ;
                    if (i<10){
                        str = imgName[animNumber] +"0"+ i;
                    }else {
                        str = imgName[animNumber] + i;
                    }

                    int id = getResources().getIdentifier(str, "mipmap", getActivity().getPackageName());
                    Log.d("TAG", id + "========" + R.mipmap.kath0000);
                    Bitmap tree = comp(BitmapFactory.decodeResource(getResources(), id));
//                Bitmap tree = BitmapFactory.decodeResource(getResources(),id);
                    Drawable drawable = new BitmapDrawable(tree);
                    frameAnim.addFrame(drawable, 84);
                }
                anims[animNumber] = frameAnim;
                frameAnim = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }*/
    /*private Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    private Bitmap comp(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }*/



}
