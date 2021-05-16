
package com.example;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class List {

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
    public java.util.List<Weather> weather = null;

}
