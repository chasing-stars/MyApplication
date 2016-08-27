package com.example.edward1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.yanzhenjie.andserver.AndServer;
import com.yanzhenjie.andserver.AndServerBuild;

public class MyService extends Service {
    public MyService() {
    }


    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public  MyService getService() {
            // Return this instance of LocalService so clients can call public methods
            return MyService.this;
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("fuck", "MyService onCreate:");
    }

    public  void  startUp(){
        AndServerBuild andServerBuild = AndServerBuild.create();
        andServerBuild.setPort(8999);// 指定http端口号。

        // 注册接口。
        andServerBuild.add("test", new AndServerTestHandler());
        // 这里还可以注册很多接口。

        // 启动服务器。
        andServer= andServerBuild.build();
        andServer.launch();
    }

    AndServer andServer;
    public  void tearDown(){
        if(andServer != null && andServer.isRunning()) {
            andServer.close();
        }
    }
}
