package com.example.lsahi.mytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


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
}
