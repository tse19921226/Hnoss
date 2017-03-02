package com.jartcorp.hnoss.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jartcorp.hnoss.LogingPage;
import com.jartcorp.hnoss.MainActivity;
import com.jartcorp.hnoss.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class LoginFragment extends android.support.v4.app.Fragment {


    private  boolean result;
    EditText Et_account,Et_password,Et_name,Et_email;
    String account,password,name,e_mail;
    TextView ResTextGO;
    Button Logbtn;
    Button LogOut;
    public static final String TAG = "content";
    public static final String r="gogo";
    private View view;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private static final String STRINGURL="http://52.76.46.202/r_2";
    private String content;
    private Context LOGctx;
    private boolean test=true;
    private JSONObject jsonObject;
    private Object jsonOBJ;


    public static LoginFragment newInstance(String content) {
        LoginFragment fragment = new LoginFragment();
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_login, container, false);
        init();
        ResTextGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), LogingPage.class);
                getActivity().startActivity(intent);
            }
        });
        Logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = "http://52.76.46.202/l";
                LOGctx = getActivity().getApplicationContext();
                requestQueue= Volley.newRequestQueue(LOGctx);
                stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Log.i("TAG", "++++++++++++++++++++");
                        try {
                            jsonObject = new JSONObject(response);
                            System.out.println(response);
                            Log.i("TAG", "998989898989898989");
                            jsonOBJ=jsonObject.get("result");
                            System.out.println(jsonOBJ);
                            Log.i("TAG", "REREREREREREREREREREERERE");
                            result=(boolean)jsonOBJ;
                            System.out.println(result);

                            if (result==true){
                                Intent rere=new Intent();
                                rere.putExtra("res",result);
                                rere.setClass(getActivity(), MainActivity.class);
                                getActivity().startActivity(rere);
                                Toast.makeText(LOGctx, "登入成功", Toast.LENGTH_LONG).show();
                                view= inflater.inflate(R.layout.fragment_logout, container, false);
                            }else {
                                Toast.makeText(LOGctx,"帳號密碼錯誤",Toast.LENGTH_LONG).show();
                                //view= inflater.inflate(R.layout.fragment_login, container, false);

                            }
                        }catch (JSONException e) {
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
                        hashMap.put("account",Et_account.getText().toString());
                        hashMap.put("password",Et_password.getText().toString());
                        hashMap.put("s_token","7H2IIlsaE5zU2afTsiOA0G7Yh3vvnCPaT7qpTK8e0ei4jNLUp9SdnwtXPDVBQG271paZs1b9Jfo6YiEOJSjI7fdPK5HAGCCXxAiq");
                        return hashMap;
                    }
                };

                requestQueue.add(stringRequest);


            }
        });


        return view;
    }

    private void init(){
        Et_account=(EditText)view.findViewById(R.id.logAccount);
        Et_password=(EditText)view.findViewById(R.id.logPassword);
        Logbtn=(Button)view.findViewById(R.id.LogBtn);
        ResTextGO=(TextView)view.findViewById(R.id.ResGO);
        LogOut=(Button)view.findViewById(R.id.Logout);
    }






}
