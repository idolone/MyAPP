package com.example.myweather;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OtherTipsBean  implements Serializable {
    @SerializedName("title")
    private String air_tips;

    @SerializedName("level")
    private String level;

    @SerializedName("desc")
    private String desc;

    public String getAir_tips() {
        return air_tips;
    }

    public void setAir_tips(String air_tips) {
        this.air_tips = air_tips;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "OtherTipsBean{" +
                "air_tips='" + air_tips + '\'' +
                ", level='" + level + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
