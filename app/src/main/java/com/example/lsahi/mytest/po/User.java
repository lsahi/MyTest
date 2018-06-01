package com.example.lsahi.mytest.po;

/**
 * Created by lsahi on 2018/5/31.
 */

public class User {

    String sno;
    String sname;
    String password;
    String smail;
    String phone;

    int gender;
    int type1;
    int type2;
    int type3;
    int type4;

    public String getSno() {
        return sno;
    }
    //
    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password=password;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return smail;
    }
    public void setMail(String smail) {
        this.smail=smail;
    }

    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender=gender;
    }


    public void setType1(int type1){
        this.type1=type1;
    }
    public void setType2(int type2){
        this.type2=type2;
    }
    public void setType3(int type3){
        this.type3=type3;
    }
    public void setType4(int type4){
        this.type4=type4;
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

}
