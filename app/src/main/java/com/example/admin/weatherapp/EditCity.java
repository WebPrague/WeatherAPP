package com.example.admin.weatherapp;

/**
 * Created by admin on 2017/8/17.
 */

public class EditCity {
    private String edit_city_detail;
    private String edit_city_province;
    private int edit_delete_imageid;

    public EditCity(String edit_city_detail, String edit_city_province,int edit_delete_imageid) {
        this.edit_city_detail = edit_city_detail;
        this.edit_city_province = edit_city_province;
        this.edit_delete_imageid = edit_delete_imageid;
    }

    public String getEdit_city_detail() {
        return edit_city_detail;
    }

    public void setEdit_city_detail(String edit_city_detail) {
        this.edit_city_detail = edit_city_detail;
    }

    public String getEdit_city_province() {
        return edit_city_province;
    }

    public void setEdit_city_province(String edit_city_province) {
        this.edit_city_province = edit_city_province;
    }

    public int getEdit_delete_imageid() {
        return edit_delete_imageid;
    }

    public void setEdit_delete_imageid(int edit_delete_imageid) {
        this.edit_delete_imageid = edit_delete_imageid;
    }
}
