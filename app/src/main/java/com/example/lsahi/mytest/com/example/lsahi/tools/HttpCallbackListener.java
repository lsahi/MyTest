package com.example.lsahi.mytest.com.example.lsahi.tools;

/**
 * Created by lsahi on 2018/5/29.
 */

public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);

}
