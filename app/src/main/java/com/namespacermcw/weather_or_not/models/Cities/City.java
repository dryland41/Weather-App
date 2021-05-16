package com.namespacermcw.weather_or_not.models.Cities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class City {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("coord")
    @Expose
    public Coord coord;
    @SerializedName("main")
    @Expose
    public Main main;
    @SerializedName("dt")
    @Expose
    public Integer dt;
    @SerializedName("wind")
    @Expose
    public Wind wind;
    @SerializedName("sys")
    @Expose
    public Sys sys;
    @SerializedName("rain")
    @Expose
    public Object rain;
    @SerializedName("snow")
    @Expose
    public Object snow;
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;
    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;
}
