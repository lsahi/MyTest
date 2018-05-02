package com.example.lsahi.mytest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;
    private ImageView imageView;
    private DrawerLayout mDrawerLayout;
    public int loginstatus = 0;


    //private ScrollView scrollView = null;
    private int buttonCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView=(NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ls_menu);
        }
        //navView.setCheckedItem(R.id.nav_call);
        //navView.setOnClickListener(this);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                switch(item.getItemId()){
                    case R.id.nav_profile:
                        Intent intentLogin=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intentLogin);
                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                }
                return true;
            }
        });
       // scrollView = (ScrollView) findViewById(R.id.scrollView);


        Button button1=(Button) findViewById(R.id.button_1);
        Button button2=(Button) findViewById(R.id.button_2);
        Button button3=(Button) findViewById(R.id.button_3);
        Button button4=(Button) findViewById(R.id.button_4);
        Button button5=(Button) findViewById(R.id.button_5);
        Button button6=(Button) findViewById(R.id.button_6);
        Button button7=(Button) findViewById(R.id.button_7);

        editText =(EditText) findViewById(R.id.edit_text);
        imageView=(ImageView)findViewById(R.id.image_view);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        /*button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });*/
    }

    //home menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;

        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("chenge my wife");
                dialog.setMessage("WILL YOU REALLY CHANGE YOUR WIFE?");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (buttonCount == 1) {
                            imageView.setImageResource(R.drawable.img_1);
                            buttonCount = 0;
                        } else {
                            imageView.setImageResource(R.drawable.img_2);
                            buttonCount = 1;
                        }
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                    ;
                });
                dialog.show();
                //imageView.setImageResource(R.drawable.img_2);
                break;

            case R.id.button_2:
                Intent intent2=new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent2);
                break;

            case R.id.button_3:
                Intent intent3=new Intent(MainActivity.this,BroadcastActivity.class);
                startActivity(intent3);
                break;

            case R.id.button_4:
                Intent intent4=new Intent(MainActivity.this,MessageActivity.class);
                startActivity(intent4);
                break;
            case R.id.button_5:
                Intent intent5=new Intent(MainActivity.this,NotificationTest.class);
                startActivity(intent5);
                break;
            case R.id.button_6:
                Intent intent6=new Intent(MainActivity.this,WebActivity.class);
                startActivity(intent6);
                break;
            case R.id.button_7:
                Intent intent7=new Intent(MainActivity.this,PositionActivity.class);
                startActivity(intent7);
                break;
            default:
                break;//
        }
    }
}
