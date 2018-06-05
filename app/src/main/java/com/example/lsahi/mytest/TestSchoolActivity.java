package com.example.lsahi.mytest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lsahi.mytest.com.example.lsahi.tools.MyDatabaseHelper;
import com.example.lsahi.mytest.po.School;
import com.example.lsahi.mytest.com.example.lsahi.tools.SchoolItems;
import com.example.lsahi.mytest.po.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestSchoolActivity extends AppCompatActivity implements View.OnClickListener{

    private String loggedName;
    private TextView myname;

    private EditText searchBar;
    private String keyword="";

    private SwipeRefreshLayout swipeRefresh;
    private DrawerLayout mDrawerLayout;
    private static int[] randImg={R.drawable.lsahi7,R.drawable.lsahi6,R.drawable.lsahi5,R.drawable.lsahi4,R.drawable.lsahi3,R.drawable.lsahi2,R.drawable.lsahi1,R.drawable.lsahi0};

    private User user;

    private String showAllActivities="http://49.140.122.169:8080/GuangyanAdmin/showAllActivities.do";

    private String showSelectedActivities="http://49.140.122.169:8080/GuangyanAdmin/showSelectedActivities.do";
    private String url="";

    /*private School[] schools={
            new School("akagi","Kancolle",R.drawable.akagi),
            new School("kaga","Kancolle",R.drawable.kaga),
            new School("shogaku","Kancolle",R.drawable.shogaku),
            new School("zuikaku","Kancolle",R.drawable.zuigaku),
            new School("akatsuki","Kancolle",R.drawable.akatsuki),
            new School("hibiki","Kancolle",R.drawable.hibiki),
            new School("ikanari","Kancolle",R.drawable.ikanari),
            new School("inazuma","Kancolle",R.drawable.inazuma),
            new School("kongo","Kancolle",R.drawable.kongo),
            new School("hie","Kancolle",R.drawable.hie),
            new School("haruna","Kancolle",R.drawable.haruna),
            new School("kirishima","Kancolle",R.drawable.kirishima),
            new School("yuudachi","Kancolle",R.drawable.yuudachi),
            new School("shigure","Kancolle",R.drawable.shigure),
            new School("eugen","Kancolle",R.drawable.eugen),
            new School("bismarck","Kancolle",R.drawable.bismarck),*/

           //offline test here
            /*
            new School("1","lsahi","software",randImg),
            new School("2","lsahi1","computer",randImg),
            new School("3","lsahi1","steam",randImg),
            new School("4","lsahi1","ubisoft",randImg),
            new School("5","lsahi5","google",randImg),
            new School("6","lsahi2","android",randImg),
            new School("7","lsahi3","iphone",randImg),
            new School("8","lsahi4","windows",randImg)

    };*/



    private List<School> schoolList=new ArrayList<>();

    private SchoolItems.SchoolAdapter adapter;

    private MyDatabaseHelper dbHelper;

    private SearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_school);
        initSchools(keyword);

        dbHelper=new MyDatabaseHelper(this,"user.db",null,1);
        dbHelper.getWritableDatabase();

        user=dbHelper.getUser(dbHelper.getWritableDatabase());
        loggedName=user.getSname();

        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView=(NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navView.inflateHeaderView(R.layout.nav_header);
        myname = (TextView) headerLayout.findViewById(R.id.username);
        navHeaderChange();

        searchBar=(EditText) findViewById(R.id.searchbar);

        //pic listener(?)
        searchBar.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = searchBar.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP){
                    return false;
                }
                if (event.getX() > searchBar.getWidth()
                        - searchBar.getPaddingRight()
                        - drawable.getIntrinsicWidth()){
                    keyword=new String(searchBar.getText().toString());
                    refreshSchools();
                }
                return false;
            }

        });


        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ls_menu);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                switch(item.getItemId()){
                    case R.id.nav_login:
                        if(dbHelper.inDatabase(dbHelper.getWritableDatabase())!=0){
                            Toast.makeText(TestSchoolActivity.this,"您已登陆，无需再次登录",Toast.LENGTH_SHORT).show();
                        }else{
                            Intent intentLogin=new Intent(TestSchoolActivity.this,LoginActivity.class);
                            startActivity(intentLogin);
                        }
                        break;
                    case R.id.nav_profile:
                        if(dbHelper.inDatabase(dbHelper.getWritableDatabase())==0){
                            Toast.makeText(TestSchoolActivity.this,"对不起，您尚未登录",Toast.LENGTH_SHORT).show();
                            break;
                        }else {
                            Intent intentProfile = new Intent(TestSchoolActivity.this, ProfileActivity.class);
                            intentProfile.putExtra(AddActivity.USER_NAME,loggedName);
                            startActivity(intentProfile);
                            break;
                        }
                    //goto usercheck mission
                    case R.id.nav_usercheck:
                        if(dbHelper.inDatabase(dbHelper.getWritableDatabase())==0){
                            Toast.makeText(TestSchoolActivity.this,"对不起，您尚未登录",Toast.LENGTH_SHORT).show();
                            break;
                        }else {
                            Intent intentCheck = new Intent(TestSchoolActivity.this, UploadPositionActivity.class);
                            intentCheck.putExtra(AddActivity.USER_NAME,loggedName);
                            startActivity(intentCheck);
                            break;
                        }
                    case R.id.nav_logout:
                        //pref.
                        if(dbHelper.inDatabase(dbHelper.getWritableDatabase())==0){
                            Toast.makeText(TestSchoolActivity.this,"尚未登录",Toast.LENGTH_SHORT).show();
                        }else{
                            //delete
                            dbHelper.clearDatabase(dbHelper.getWritableDatabase());
                            navHeaderChange();
                            Toast.makeText(TestSchoolActivity.this,"用户已离线，如需使用请再次登录",Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        mDrawerLayout.closeDrawers();
                }
                return true;
            }
        });

        FloatingActionButton add=(FloatingActionButton)findViewById(R.id.add);
        add.setOnClickListener(this);

        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.school_recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SchoolItems.SchoolAdapter(schoolList);
        recyclerView.setAdapter(adapter);
        swipeRefresh=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshSchools();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                if(dbHelper.inDatabase(dbHelper.getWritableDatabase())==0){
                    Toast.makeText(TestSchoolActivity.this,"对不起，您尚未登录",Toast.LENGTH_SHORT).show();
                    break;
                }else {
                    Intent intent2 = new Intent(TestSchoolActivity.this, AddActivity.class);
                    intent2.putExtra(AddActivity.USER_NAME,loggedName);
                    startActivity(intent2);
                    break;
                }
            default:
                break;
        }
    }

    private void refreshSchools(){

        Toast.makeText(TestSchoolActivity.this,"正在向服务器请求数据，请稍候...如果时间过长请手动刷新一次",Toast.LENGTH_SHORT).show();
        /*new Thread(new Runnable(){
            @Override
            public  void run(){
                try{
                    Thread.sleep(1300);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //no change in internet
                        refresh();

                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);

                    }
                });
            }
        }).start();
        */
        refresh();

        adapter.notifyDataSetChanged();
        swipeRefresh.setRefreshing(false);
    }

    // get from the Internet, refresh to get again
    private void initSchools(String keyword){
        schoolList.clear();

        if(keyword.equals("")) {
            sendRequestWithOkhttp(showAllActivities);
        }else{
            url=showSelectedActivities+"?keyword="+keyword;
            sendRequestWithOkhttp(url);
        }
        //
        /**
         * offline test
         */
        /*
        for(int i=0;i<50;i++){
            Random random=new Random();
            int index=random.nextInt(schools.length);
            schoolList.add(schools[index]);
        }
        */

        //
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
                    jsonToList(responseData);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void jsonToList(String myJson){
        /**
         * 字符串转json对象
         */
        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(myJson).getAsJsonArray();
        Gson gson=new Gson();
        for (JsonElement gotActivity : jsonArray){
            School gotSchool=gson.fromJson(gotActivity, School.class);
            Random rand = new Random();
            int randNum = rand.nextInt(7);
            gotSchool.setActivityImageId(randImg[randNum]);
            schoolList.add(gotSchool);
        }
    }

// this loginChack also offers username
    public int loginStatusCheck(){
        SharedPreferences pref=getSharedPreferences("localLogin",MODE_PRIVATE);
        String name=pref.getString("name","");
        int status=pref.getInt("status",0);
        //status =1
        loggedName=name;
        return status;
    }

    //在handler中更新UI
    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {

            if((loggedName instanceof String)!=true) {
                loggedName="";
                myname.setText("游客，请登录");
            }else{
                if(loggedName.equals("")){
                    myname.setText("游客，请登录");
                }else {
                    myname.setText(loggedName);
                }
            }
        }
    };

    //change UI
    private void navHeaderChange(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message=new Message();
                message.what=1;
                mHandler.sendMessage(message);
            }
        }).start();
    }

    //在handler中更新UI
    private Handler refreshHandler = new Handler(){
        public void handleMessage(Message msg) {

            initSchools(keyword);

        }
    };

    //change UI
    private void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message=new Message();
                message.what=1;
                refreshHandler.sendMessage(message);
            }
        }).start();
    }
}
