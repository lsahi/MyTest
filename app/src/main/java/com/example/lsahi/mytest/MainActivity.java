package com.example.lsahi.mytest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;
    private ImageView imageView;

    //private ScrollView scrollView = null;
    private int buttonCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // scrollView = (ScrollView) findViewById(R.id.scrollView);


        Button button1=(Button) findViewById(R.id.button_1);
        Button button2=(Button) findViewById(R.id.button_2);
        Button button3=(Button) findViewById(R.id.button_3);
        Button button4=(Button) findViewById(R.id.button_4);
        Button button5=(Button) findViewById(R.id.button_5);

        editText =(EditText) findViewById(R.id.edit_text);
        imageView=(ImageView)findViewById(R.id.image_view);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        /*button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });*/
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
            default:
                break;//
        }
    }
}
