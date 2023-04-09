package com.kemia.myapplication.Data;

public class Nguoidung {
    //fields
    Long id_User;
    String daxem,yeuthich;
    //constructor

    public Nguoidung(String daxem ,String yeuthich ,Long id_User) {
        this.id_User=id_User;
        this.daxem = daxem;
        this.yeuthich = yeuthich;
    }

    //methods

    public Long getId_User() {
        return id_User;
    }

    public void setId_User(Long id_User) {
        this.id_User = id_User;
    }

    public String getDaxem() {
        return daxem;
    }

    public void setDaxem(String daxem) {
        this.daxem = daxem;
    }

    public String getYeuthich() {
        return yeuthich;
    }

    public void setYeuthich(String yeuthich) {
        this.yeuthich = yeuthich;
    }


}
