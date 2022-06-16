package com.example.cp_admin_pannel;

public class report_retrieve {
    private String edt_date;
    private String edtlocation;
    private String edttime;
    private String edtdeatail;
    private String imgincident;

    public report_retrieve() {
    }

    public report_retrieve(String edt_date, String edtlocation, String edttime, String edtdeatail, String imgincident) {
        this.edt_date = edt_date;
        this.edtlocation = edtlocation;
        this.edttime = edttime;
        this.edtdeatail = edtdeatail;
        this.imgincident = imgincident;
    }

    public String getEdt_date() {
        return edt_date;
    }

    public void setEdt_date(String edt_date) {
        this.edt_date = edt_date;
    }

    public String getEdtlocation() {
        return edtlocation;
    }

    public void setEdtlocation(String edtlocation) {
        this.edtlocation = edtlocation;
    }

    public String getEdttime() {
        return edttime;
    }

    public void setEdttime(String edttime) {
        this.edttime = edttime;
    }

    public String getEdtdeatail() {
        return edtdeatail;
    }

    public void setEdtdeatail(String edtdeatail) {
        this.edtdeatail = edtdeatail;
    }

    public String getImgincident() {
        return imgincident;
    }

    public void setImgincident(String imgincident) {
        this.imgincident = imgincident;
    }
}
