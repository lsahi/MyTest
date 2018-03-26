package com.example.lsahi.mytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1=(Button) findViewById(R.id.button_1);
        editText =(EditText) findViewById(R.id.edit_text);
        imageView=(ImageView)findViewById(R.id.image_view);
        button1.setOnClickListener(this);/*(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //
            }
        });*/
    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button_1:
                imageView.setImageResource(R.drawable.img_2);
                break;
            default:
                break;
        }
    }
}
