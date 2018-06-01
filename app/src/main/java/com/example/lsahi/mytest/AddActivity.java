package com.example.lsahi.mytest;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.lsahi.mytest.com.example.lsahi.tools.MyDatabaseHelper;
import com.example.lsahi.mytest.po.User;

public class AddActivity extends AppCompatActivity {


    public static final String USER_NAME="user_name";
    private String userName;

    private String address="http://49.140.122.169:8080/GuangyanAdmin/addActivity.jsp";
    private String loggedUser;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent=getIntent();

        loggedUser=intent.getStringExtra(USER_NAME);

        WebView webView=(WebView) findViewById(R.id.add_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        String url=address+"?host="+loggedUser;
        webView.loadUrl(url);

        Toolbar toolbar=(Toolbar) findViewById(R.id.add_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
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
}