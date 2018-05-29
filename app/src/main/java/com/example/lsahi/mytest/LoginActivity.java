package com.example.lsahi.mytest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private String userId;
    private String userName;
    private String password;
    private String method;

    //server
    private String address="http://49.140.122.169:8080/GuangyanAdmin/";
    private int login=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText inputUserName=(EditText) findViewById(R.id.login_name);
        EditText inputPassword=(EditText) findViewById(R.id.login_password);
        userName=inputUserName.getText().toString();
        password=inputPassword.getText().toString();

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

    //set local loginstatus
    public void localLogin(String userName) {
        SharedPreferences.Editor editor = getSharedPreferences("localLogin", MODE_PRIVATE).edit();
        editor.putString("uid",userId);
        editor.putString("name", userName);
        editor.putInt("status", 1);
    }
        /*
        FileOutputStream outUser=null;
        FileOutputStream outStatus=null;
        BufferedWriter writer=null;
        try{
            outUser=openFileOutput("userdata", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(outUser));
            writer.write("userName");
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try{
            outStatus=openFileOutput("loginstatus", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(outStatus));
            writer.write((int)1);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }*/
    public void login(String userName, String password){
        method=new String("studentLogin.do");
        address=address+method+"?sname="+userName+"&password="+password;
    }




    //check login status if return=1,logged
    public int loginStatusCheck(){
        SharedPreferences pref=getSharedPreferences("localLogin",MODE_PRIVATE);
        String id=pref.getString("userId","");
        String name=pref.getString("name","");
        int status=pref.getInt("status",1);
        return  status;
    }
}
