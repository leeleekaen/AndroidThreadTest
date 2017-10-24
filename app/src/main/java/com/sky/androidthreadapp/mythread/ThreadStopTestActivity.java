package com.sky.androidthreadapp.mythread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sky.androidthreadapp.R;

/**
 * Created by Administrator on 2017/10/14.
 */

public class ThreadStopTestActivity extends AppCompatActivity {

    private MyThread myThread = new MyThread();
    private SleepThread sleepThread = new SleepThread();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mHandler.post(runnable); // handler运行runnable
                goThread();
            }
        });
        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mHandler.post(runnable); // handler运行runnable
                stopThread();
            }
        });
    }

    public void goThread(){
//        if(null == myThread){
//            myThread = new MyThread();
//        }
//        myThread.start();
        if(null == sleepThread){
            sleepThread = new SleepThread();
        }
        sleepThread.start();
    }
    // 定义开始和结束线程的方法，与按钮绑定
    private void stopThread() {
//        if(null != myThread && myThread.isAlive()){
//            myThread.interrupt();
//            myThread = null;
//        }
        if(null != sleepThread && sleepThread.isAlive()){
            sleepThread.interrupt();
            sleepThread = null;
        }
    }

    public class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            int i = 0;
            // 判断状态，如果被打断则跳出并将线程置空
            while (!isInterrupted()){
                i++;
                Log.i("thread",Thread.currentThread().getName()+":Running()_Count:"+i);
            }
        }
    }

    public class SleepThread extends Thread{
        @Override
        public void run() {
            super.run();
            int i = 0;
            while(!isInterrupted()){ // 判断线程是否被打断
                try {
                    i++;
                    Thread.sleep(1000);
                    Log.i("thread",Thread.currentThread().getName()+":Running()_Count:"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.i("thread",Thread.currentThread().getName()+"异常抛出，停止线程");
                    break;// 抛出异常跳出循环
                }
            }

        }
    }





    //    public volatile boolean stop = false;
//    @Override
//    public void run() {
//        super.run();
//        while (!stop){
//            // thread runing
//        }
//    }
}
