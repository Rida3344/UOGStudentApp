package com.example.semesterfreeze;

public class SemesterFreeze {
    public SemesterFreeze() {
    }

    public SemesterFreeze(int id,String currentsamester, String samesterfreeze, String reason) {
        this.currentsamester = currentsamester;
        this.samesterfreeze = samesterfreeze;
        this.Reason = reason;
        this.id = id;
    }


    public String getCurrentsamester() {
        return currentsamester;
    }

    public void setCurrentsamester(String currentsamester) {
        this.currentsamester = currentsamester;
    }

    public String getSamesterfreeze() {
        return samesterfreeze;
    }

    public void setSamesterfreeze(String samesterfreeze) {
        this.samesterfreeze = samesterfreeze;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String samesterfreeze;
    String currentsamester;
    String Reason;
    int id;
}
