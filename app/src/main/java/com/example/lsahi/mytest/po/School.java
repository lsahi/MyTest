package com.example.lsahi.mytest.po;

import java.util.Random;

/**
 * Created by lsahi on 2018/5/2.
 */

public class School {

    private String id;      //long
    private String host;        //host
    private String name;    //name
    private String details;         //details
    int type1;
    int type2;
    int type3;
    int type4;
    int enabled;
    private int activityImageId;
/*
    public School(String id, String host, String name, int[] activityImageIdSet){
        this.id = id;
        this.host = host;
        this.name = name;
        Random rand = new Random();
        int randNum = rand.nextInt(7);
        this.activityImageId=activityImageIdSet[randNum];
    }
    */
    public School(int[] activityImageIdSet){
        Random rand = new Random();
        int randNum = rand.nextInt(7);
        this.activityImageId=activityImageIdSet[randNum];
    }


    public void setActivityImageId(int imageId){
        this.activityImageId=imageId;
    }
    public int getActivityImageId(){
        return activityImageId;
    }

//
    public void setId(String id) {
        this.id= id;
    }
    public String getId() {
        return id;
    }

    public void setHost(String host) {
        this.host=host;
    }
    public String getHost() {
        return host;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setDetails(String details) {
        this.details=details;
    }
    public String getDetails() {
        return details;
    }

    public void setType1(int t1) {
        this.type1=t1;
    }
    public void setType2(int t2) {
        this.type1=t2;
    }
    public void setType3(int t3) {
        this.type1=t3;
    }
    public void setType4(int t4) {
        this.type1=t4;
    }
    public int getType1() {
        return type1;
    }
    public int getType2() {
        return type2;
    }
    public int getType3() {
        return type3;
    }
    public int getType4() {
        return type4;
    }

    public void setEnabled(int enabled) {
        this.enabled=enabled;
    }
    public int getEnabled() {
        return enabled;
    }


}
