package com.sky.androidthreadapp.mythread;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sky.androidthreadapp.R;

/**
 * Created by yuetu-develop on 2017/10/13.
 */

public class ThreadTestActivity extends AppCompatActivity{

    private int count = 0;
    private Handler mHandler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.i("thread",Thread.currentThread().getName()+":"+count);
            count ++;
            mHandler.postDelayed(runnable,1000); // 执行后每1000毫秒再次加执行
        }
    };

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
    }

    @Override
    protected void onDestroy() {
        // 销毁线程，释放资源
        mHandler.removeCallbacks(runnable);
        super.onDestroy();
    }

    public void goThread(){
        new MyThread().start();
        new Thread(new MyRunnable()).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    public class MyThread extends Thread{

        @Override
        public void run() {
            super.run();
            // do something
            Log.i("thread",Thread.currentThread().getName()+":Running()");
        }
    }

    public class MyRunnable implements Runnable{

        @Override
        public void run() {
            // do something
            Log.i("thread",Thread.currentThread().getName()+":Running()");
        }
    }


}
