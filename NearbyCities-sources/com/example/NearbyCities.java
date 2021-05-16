
package com.example;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class NearbyCities {

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
    public java.util.List<com.example.List> list = null;

}
