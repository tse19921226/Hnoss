package com.jartcorp.hnoss;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jartcorp.hnoss.activity.LoginFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class LogingPage extends AppCompatActivity {
    EditText L_account,L_password,L_name,L_email;
    String account,password,name,e_mail;

    public static final String TAG = "content";
    private View view;
    private static final String STRINGURL="http://52.76.46.202/r_2";
    private String content;
    private Context ctx;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private JSONObject jsonObject;
    private Button reBtn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logingpage);
        init();
        reBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (L_account.getText().toString()==null||"".equals(L_account.getText().toString().trim())||
                        L_password.getText().toString()==null||"".equals(L_password.getText().toString().trim())||
                        L_name.getText().toString()==null||"".equals(L_name.getText().toString().trim())||
                        L_email.getText().toString()==null||"".equals(L_email.getText().toString().trim())) {

                    new AlertDialog.Builder(LogingPage.this)
                            .setTitle("錯誤").setMessage("您有資料未填").setPositiveButton("確定",null).show();
                } else {
                    final String url = "http://52.76.46.202/r_2";
                    ctx = getApplicationContext();
                    requestQueue = Volley.newRequestQueue(ctx);
                    stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println(response);
                            Log.i("TAG", "++++++++++++++++++++");
                            try {
                                jsonObject = new JSONObject(response);
                                System.out.println(response);
                                Log.i("TAG", "998989898989898989");
                                Object jsonOBJ = jsonObject;
                                System.out.println(jsonOBJ);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.i("TAG", "556161616516516515");
                            Toast.makeText(ctx, "註冊成功", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("請求錯誤:" + error.toString());
                            Log.i("TAG", "====================");
                            Toast.makeText(ctx, "註冊失敗...", Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected HashMap<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("account", L_account.getText().toString());
                            hashMap.put("password", L_password.getText().toString());
                            hashMap.put("name", L_name.getText().toString());
                            hashMap.put("email", L_email.getText().toString());
                            Log.i("TAG", "************************");
                            System.out.println(L_email);
                            hashMap.put("s_token", "7H2IIlsaE5zU2afTsiOA0G7Yh3vvnCPaT7qpTK8e0ei4jNLUp9SdnwtXPDVBQG271paZs1b9Jfo6YiEOJSjI7fdPK5HAGCCXxAiq");
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);

                }
            }
        });


    }




    private void init(){
        L_account=(EditText)findViewById(R.id.regAccount);
        L_password=(EditText)findViewById(R.id.regPassword);
        L_name=(EditText)findViewById(R.id.regName);
        L_email=(EditText)findViewById(R.id.regEmail);
        reBtn=(Button)findViewById(R.id.regBtn);
    }

}
