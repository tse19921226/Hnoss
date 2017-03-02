package com.jartcorp.hnoss.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.ImageView;

import com.jartcorp.hnoss.R;

/**
 * Created by yeh on 15/9/3.
 */
public class MyWallpaperService extends WallpaperService {


    @Override
    public void onCreate() {
        System.out.println("service onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println("service onDestory");
        super.onDestroy();
    }

    @Override
    public Engine onCreateEngine() {
        return new MyEngine();
    }

    //設定自訂的Engine
    class MyEngine extends Engine{
        WallpaperThread wallpaperThread;
        public MyEngine() {
            SurfaceHolder surfaceHolder = getSurfaceHolder();
            wallpaperThread = new WallpaperThread(surfaceHolder);

        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            //設定動態桌布可以互動觸控事件
            setTouchEventsEnabled(true);

        }

        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            super.onSurfaceCreated(holder);
            //開始執行動態桌布
            wallpaperThread.start();
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            //結束動態桌布
            wallpaperThread.stopRunning();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            //讓GC回收wallpaperThread
            wallpaperThread = null;
        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            //執行wallpaperThread裡的觸控事件
            wallpaperThread.doTouchEvent(event);
        }
    }
}
