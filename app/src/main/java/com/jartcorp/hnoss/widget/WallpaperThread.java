package com.jartcorp.hnoss.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import java.util.ArrayList;

/**
 * Created by yeh on 15/9/3.
 */
public class WallpaperThread extends Thread {
    //run為ture表示不斷檢查並執行消圓動作
    boolean run = true;
    //wait為true表示無圓可消，所以暫停消圓動作
    boolean wait = true;

    //SurfaceHolder用來取得Canvas
    SurfaceHolder surfaceHolder;
    Canvas canvas;
    Paint paint;
    //用來紀錄已產生的圓資料，待處理
    ArrayList<Circle> pastCircles = new ArrayList<>();
    //用來紀錄要從pastCircles中刪去的圓資料，因為不能在ArrayList遍歷元素時刪除元素，否則會有ConcurrentModificationException
    ArrayList<Circle> circlesToBeRemoved = new ArrayList<>();
    public WallpaperThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        //設定畫圓用的畫筆顏色
        paint = new Paint();
        paint.setColor(Color.BLUE);
    }

    @Override
    public void run() {
        canvas = this.surfaceHolder.lockCanvas(null);
        //繪製初始背景
        canvas.drawColor(Color.BLACK);
        surfaceHolder.unlockCanvasAndPost(canvas);
        while (run) {
            if (wait) {
                try {
                    synchronized (this) {
                        //沒有圓可消時，讓Thread等待
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                synchronized (pastCircles) {
                    //將圓刪掉(背景重新塗色)
                    canvas = surfaceHolder.lockCanvas(null);
                    canvas.drawColor(Color.BLACK);
                    //檢查pastCircles中有無待處理的圓
                    for (Circle circle : pastCircles) {
                        //如果圓的半徑已被減0以下，就將其從pastCircles中刪除
                        if (circle.getRadius() <= 0) {
                            circlesToBeRemoved.add(circle);
                        } else {
                            //畫圓
                            canvas.drawCircle(circle.getCenterX(), circle.getCenterY(), circle.getRadius(), paint);
                            //將圓半徑減1
                            circle.setRadius(circle.getRadius() - 1);
                        }
                    }
                    surfaceHolder.unlockCanvasAndPost(canvas);
                    //從pastCircles中刪去已消完的圓資料
                    pastCircles.removeAll(circlesToBeRemoved);
                    //如果pastCircles中沒有圓了，就進行等待
                    wait = (pastCircles.size() == 0);
                }
            }
        }
    }

    public void stopRunning() {
        run = false;
    }

    public void doTouchEvent(MotionEvent motionEvent) {
        //判斷Touch動作
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //用來紀錄觸控動作發生時相應的圓圈資訊
                Circle currentCircle = new Circle(motionEvent.getX(), motionEvent.getY(), 100);
                //保護pastCircles，避免跟run()中讀取pastCircles的動作起衡突
                synchronized (pastCircles) {
                    //將要處理的的圓紀錄到pastCircles中
                    pastCircles.add(currentCircle);
                    wait = false;
                    synchronized (this) {
                        //主動喚醒Thread
                        notify();
                    }
                }
                break;
        }
    }

    //用來紀錄圓的資訊
    class Circle {
        float centerX;
        float centerY;
        int radius = 100;

        public Circle(float centerX, float centerY, int radius) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.radius = radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public float getCenterX() {
            return centerX;
        }

        public float getCenterY() {
            return centerY;
        }

        public int getRadius() {
            return radius;
        }
    }
}
