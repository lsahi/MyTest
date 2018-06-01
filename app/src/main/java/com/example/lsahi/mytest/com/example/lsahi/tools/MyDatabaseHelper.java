package com.example.lsahi.mytest.com.example.lsahi.tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.lsahi.mytest.po.User;

/**
 * Created by lsahi on 2018/6/1.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    //TABLE NAME STUDENT

    public static final String CREATE_USER=
            "create table student ("
            +"Sno text primary key, "
            +"Sname text, "
            +"type1 integer, " +"type2 integer, " +"type3 integer, " +"type4 integer)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){

        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists student");
    }

    public int inDatabase(SQLiteDatabase db){
        int amount=0;
        Cursor c = db.rawQuery("select * from student", null);
        amount=c.getCount();
        return amount;
    }

    public void clearDatabase(SQLiteDatabase db){
        db.execSQL("delete from student");
    }

    public User getUser (SQLiteDatabase db){
        User user=new User();
        Cursor cursor=db.rawQuery("select * from student", null);


        if(cursor.moveToFirst()){

            do{
                String name;
                user.setSno(cursor.getString(0));
                user.setSname(cursor.getString(1));
                name=user.getSname();
                user.setType1(cursor.getInt(2));
                user.setType2(cursor.getInt(3));
                user.setType3(cursor.getInt(4));
                user.setType4(cursor.getInt(5));

                Log.d("getUsertest",name);
            }while(cursor.moveToNext());
        }
        /*
        user.setSno(cursor.getString(cursor.getColumnIndex("0")));
                user.setSname(cursor.getString(cursor.getColumnIndex("1")));
                user.setType1(cursor.getInt(cursor.getColumnIndex("2")));
                user.setType2(cursor.getInt(cursor.getColumnIndex("3")));
                user.setType3(cursor.getInt(cursor.getColumnIndex("4")));
                user.setType4(cursor.getInt(cursor.getColumnIndex("5")));
         */

        cursor.close();
        return user;
    }


}
