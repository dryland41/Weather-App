package com.namespacermcw.weather_or_not.models.Cities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CitiesWeatherHead {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("cod")
    @Expose
    public String cod;
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("list")
    @Expose
    public List<City> cityList = null;

}
