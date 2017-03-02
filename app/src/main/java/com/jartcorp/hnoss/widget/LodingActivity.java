package com.jartcorp.hnoss.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jartcorp.hnoss.MainActivity;
import com.jartcorp.hnoss.R;
import com.jartcorp.hnoss.activity.HomeFragment;

import java.util.Timer;
import java.util.TimerTask;

public class LodingActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loding);
        Thread thread = new Thread(){
            @Override
        public void run(){
                int waitingTime = 3000;
                try {
                    while(waitingTime > 0) {
                        sleep(100);
                        waitingTime -= 100; // 100ms per time
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                    Intent intent = new Intent(LodingActivity.this, MainActivity.class);
                    startActivity(intent);  // enter the main activity finally
                }
            }
        };


        thread.start();


        //Timer timer = new Timer(true);
        //timer.schedule(new timetTask(), 3000, 5000);
    }



    /*public class timetTask extends TimerTask{

        @Override
        public void run() {
            Intent it = new Intent();
            it.setClass(LodingActivity.this, MainActivity.class);
            startActivity(it);
            this.cancel();
            LodingActivity.this.finish();
        }
    }*/


}
