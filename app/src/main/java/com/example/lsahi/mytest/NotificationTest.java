package com.example.lsahi.mytest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.media.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NotificationTest extends AppCompatActivity implements View.OnClickListener{

    public static final int TAKE_PHOTO=1;

    private ImageView picture;

    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);
        Button sendNotice=(Button) findViewById(R.id.send_notice);
        Button takePhoto=(Button) findViewById(R.id.take_photo);
        picture=(ImageView) findViewById(R.id.picture);
        /*
        takePhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        */
        sendNotice.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){

            //send a notice
            case R.id.send_notice:
                Intent intent=new Intent(this, MainActivity.class);
                PendingIntent pi=PendingIntent.getActivity(this,0,intent,0);
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification =new Notification.Builder(this)
                        .setContentTitle("A Notification")
                        .setContentText("Come and see your wife")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .build();
                //Notification notification=new NotificationCompat.build();
                manager.notify(1,notification);
                break;

            //take photo
            case R.id.take_photo:
                File outputImage=new File(getExternalCacheDir(),"output_img.jpg");
                try{
                    if (outputImage.exists()){
                        outputImage.delete();
                        Toast.makeText(NotificationTest.this,"function takePhoto",Toast.LENGTH_SHORT).show();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT>=24){
                    imageUri= FileProvider.getUriForFile(NotificationTest.this,"com.example.cameraalbumtest.fileprovider",outputImage);
                }else{
                    imageUri=Uri.fromFile(outputImage);
                }
                Intent intentCamera=new Intent("android.media.action.IMAGE_CAPTURE");
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intentCamera,TAKE_PHOTO);
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK){
                    try{
                        Bitmap bitmapCamera=BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmapCamera);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }


}
