package test.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DuProject implements Serializable {
    @SerializedName("p_name")
    public String p_name;
    @SerializedName("remote")
    public String remote;
}
