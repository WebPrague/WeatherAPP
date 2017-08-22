package com.example.admin.weatherapp.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by admin on 2017/8/22.
 */
@Table(name = "WeatherCity")
public class WeatherCity {
    @Column(name = "id", isId = true, autoGen = true)
    private int id;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;


    @Column(name = "airQuality")
    private String airQuality;

    @Column(name = "hum")
    private String hum;

    @Column(name = "windDir")
    private String windDir;

    @Column(name = "tmp")
    private String tmp;

    @Column(name = "tmpMaxMin")
    private String tmpMaxMin;

    @Column(name ="condCode")
    private int condCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(String airQuality) {
        this.airQuality = airQuality;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getTmpMaxMin() {
        return tmpMaxMin;
    }

    public void setTmpMaxMin(String tmpMaxMin) {
        this.tmpMaxMin = tmpMaxMin;
    }

    public int getCondCode() {
        return condCode;
    }

    public void setCondCode(int condCode) {
        this.condCode = condCode;
    }
}
