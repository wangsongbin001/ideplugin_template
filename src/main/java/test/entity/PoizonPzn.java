package test.entity;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PoizonPzn implements Serializable{
    @SerializedName("local")
    public ArrayList<Entry> local;

    public static class Entry implements Serializable{
        @SerializedName("target")
        public String target;

        @Override
        public String toString() {
            return "Entry{" +
                    "target='" + target + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PoizonPzn{" +
                "local=" + local +
                '}';
    }
}
