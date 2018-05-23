package com.example.lsahi.mytest;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadPositionActivity extends AppCompatActivity implements View.OnClickListener{

    public LocationClient mLocationClient;

    private TextView positionText;

    //private MapView mapView;

    private BaiduMap baiduMap;

    private boolean isFirstLocate=true;

    private String mPosition;

    public static final int TAKE_PHOTO=1;

    private ImageView picture;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_upload_position);

        Button buttonRefresh=(Button) findViewById(R.id.button_refresh_position);
        Button sendNotice=(Button) findViewById(R.id.send_notice);
        Button takePhoto=(Button) findViewById(R.id.take_photo);


        positionText=(TextView) findViewById(R.id.position_text_view);
        //mapView=(MapView) findViewById(R.id.bmapView);
        //baiduMap=mapView.getMap();
        baiduMap.setMyLocationEnabled(true);

        List<String> permissionList=new ArrayList<>();

        if (ContextCompat.checkSelfPermission(UploadPositionActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(UploadPositionActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(UploadPositionActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(UploadPositionActivity.this, permissions, 1);
        }else{
            requestLocation();
        }

        picture=(ImageView) findViewById(R.id.picture);
        buttonRefresh.setOnClickListener(this);
        sendNotice.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_refresh_position:
                mLocationClient.stop();
                requestLocation();
                break;

            //send a notice => will be fixed into upload
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
                        Toast.makeText(UploadPositionActivity.this,"function takePhoto",Toast.LENGTH_SHORT).show();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT>=24){
                    imageUri= FileProvider.getUriForFile(UploadPositionActivity.this,"com.example.cameraalbumtest.fileprovider",outputImage);
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
/*
    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }
*/
    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option=new LocationClientOption();

        //option.setScanSpan(6000);
        option.setIsNeedAddress(true);

        //Using GPS *ONLY*
        //option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        mLocationClient.setLocOption(option);
    }

    private void navigateTo(BDLocation location){
        if(isFirstLocate){
            LatLng ll=new LatLng(43.82,125.27);//(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update= MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update=MapStatusUpdateFactory.zoomTo(12.5f);
            baiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }
        MyLocationData.Builder locationBuilder= new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData= locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mLocationClient.stop();
        //mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int result:grantResults){
                        if(result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"GIVEOUT ALL YOUR PERMISSIONS TO RUN THIS APP",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else{
                    Toast.makeText(this,"UNKNOWN",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    public class MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation location){
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("纬度: ").append(location.getLatitude()).append("\n");
            currentPosition.append("经度: ").append(location.getLongitude()).append("\n");
            currentPosition.append("国家: ").append(location.getCountry()).append("\n");
            currentPosition.append("省: ").append(location.getProvince()).append("\n");
            currentPosition.append("市: ").append(location.getCity()).append("\n");
            currentPosition.append("区: ").append(location.getDistrict()).append("\n");
            currentPosition.append("街道 ").append(location.getStreet()).append("\n");
            currentPosition.append("定位方式: ");
            if(location.getLocType()==BDLocation.TypeGpsLocation){
                currentPosition.append("GPS");
                navigateTo(location);
            }else if(location.getLocType()==BDLocation.TypeNetWorkLocation){
                currentPosition.append("NETWORK");
                navigateTo(location);
            }
            positionText.setText(currentPosition);
            mPosition=currentPosition.toString();
        }

    }
/*
    public class MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation location){
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("纬度: ").append(location.getLatitude()).append("\n");
            currentPosition.append("经度: ").append(location.getLongitude()).append("\n");
            currentPosition.append("国家: ").append(location.getCountry()).append("\n");
            currentPosition.append("省: ").append(location.getProvince()).append("\n");
            currentPosition.append("市: ").append(location.getCity()).append("\n");
            currentPosition.append("区: ").append(location.getDistrict()).append("\n");
            currentPosition.append("街道 ").append(location.getStreet()).append("\n");
            currentPosition.append("定位方式: ");
            if(location.getLocType()==BDLocation.TypeGpsLocation){
                currentPosition.append("GPS");
            }else if(location.getLocType()==BDLocation.TypeNetWorkLocation){
                currentPosition.append("NETWORK");
            }
            positionText.setText(currentPosition);
        }
    }
*/
    //taken photo
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
