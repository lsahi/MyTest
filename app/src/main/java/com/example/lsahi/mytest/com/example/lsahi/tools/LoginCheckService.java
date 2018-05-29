package com.example.lsahi.mytest.com.example.lsahi.tools;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

public class LoginCheckService extends Service {
    public LoginCheckService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public int loginStatusCheck(){
        SharedPreferences pref=getSharedPreferences("localLogin",MODE_PRIVATE);
        String name=pref.getString("name","");
        int status=pref.getInt("status",1);
        return status;
    }
}
