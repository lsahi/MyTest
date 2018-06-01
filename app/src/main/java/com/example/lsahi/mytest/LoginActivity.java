package com.example.lsahi.mytest;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lsahi.mytest.com.example.lsahi.tools.MyDatabaseHelper;
import com.example.lsahi.mytest.po.User;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private String userId;
    private String userName;
    private String password;
    private String method;
    private int type1;
    private int type2;
    private int type3;
    private int type4;

    private MyDatabaseHelper dbHelper;

    private User user;

    //server
    private String address="http://49.140.122.169:8080/GuangyanAdmin/studentLogin.do";
    private int login=0;

    private EditText inputUserName;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper=new MyDatabaseHelper(this,"user.db",null,1);
        dbHelper.getWritableDatabase();

        inputUserName=(EditText) findViewById(R.id.login_name);
        inputPassword=(EditText) findViewById(R.id.login_password);

        Button button1=(Button) findViewById(R.id.login);
        Button button2=(Button) findViewById(R.id.jumpToSignup);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        Toolbar toolbar=(Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jumpToSignup:
                Intent intentSignup=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intentSignup);
                break;

            case R.id.login:

                userName=inputUserName.getText().toString();
                password=inputPassword.getText().toString();
                login(userName,password);

            default:
                break;//
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void login(String userName, String password){

        String url=address+"?user="+userName+"&password="+password;
        //String net="http://49.140.122.169:8080/GuangyanAdmin/studentLogin.do?user=la&password=000000";
        sendRequestWithOkhttp(url);

    }

    private void sendRequestWithOkhttp(final String myUrl){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url(myUrl)
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();

                    int badLoginCheck=responseData.indexOf("bad login");

                    if(badLoginCheck!=-1){

                        loginFail();

                    }else{
                        //localLogin(jsonToFile(responseData));

                        String convertedJson=responseData.replace("[","");
                        convertedJson=convertedJson.replace("]","");
                        jsonToBean(convertedJson);
                        loginSuccess();

                    }
                    //jsonToList(responseData);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     *
     * @param myJson
     */
    private void jsonToBean(String myJson){
        Gson gson=new Gson();
        user=gson.fromJson(myJson, User.class);
    }
    //set local loginstatus
    public void localLogin(User user) {

        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ContentValues values= new ContentValues();

        values.put("Sno",user.getSno());
        values.put("Sname",user.getSname());
        values.put("type1",user.getType1());
        values.put("type2",user.getType2());
        values.put("type3",user.getType3());
        values.put("type4",user.getType4());

        db.insert("student",null,values);

        Log.d("LoginActivity","localLogin");
        Log.d("LoginActivity","is in db?:"+dbHelper.inDatabase(db));

    }

    //loginfail
    private Handler loginFailHandler = new Handler(){
        public void handleMessage(Message msg) {
            Toast.makeText(LoginActivity.this,"登录失败，请重试",Toast.LENGTH_SHORT).show();
        }
    };
    private void loginFail(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message=new Message();
                message.what=1;
                loginFailHandler.sendMessage(message);
            }
        }).start();
    }



    private Handler loginSuccessHandler = new Handler(){
        public void handleMessage(Message msg) {

            localLogin(user);

            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

            Intent toMainIntent=new Intent(LoginActivity.this, TestSchoolActivity.class);
            startActivity(toMainIntent);
        }
    };
    private void loginSuccess(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message=new Message();
                message.what=1;
                loginSuccessHandler.sendMessage(message);
            }
        }).start();
    }



}
