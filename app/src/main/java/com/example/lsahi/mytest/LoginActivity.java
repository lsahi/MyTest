package com.example.lsahi.mytest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private String userName;
    private int login=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button1=(Button) findViewById(R.id.login);
        Button button2=(Button) findViewById(R.id.jumpToSignup);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jumpToSignup:
                Intent intentSignup=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intentSignup);
                break;

            default:
                break;//
        }
    }

    //set local loginstatus
    public void localLogin(String userName) {
        SharedPreferences.Editor editor = getSharedPreferences("localLogin", MODE_PRIVATE).edit();
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




    //check login status if return=1,logged
    public int loginStatusCheck(){
        SharedPreferences pref=getSharedPreferences("localLogin",MODE_PRIVATE);
        String name=pref.getString("name","");
        int status=pref.getInt("status",1);
        return  status;
    }
}
