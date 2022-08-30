package com.example.semesterfreeze;

public class logincreds {
    String uname,pass;

    public logincreds(String uname, String pass) {
        this.uname = uname;
        this.pass = pass;
    }
    public logincreds(){}

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
