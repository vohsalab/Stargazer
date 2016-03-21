package com.firstsputnik.stargazer.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Response {

    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("risetime")
    @Expose
    private Integer risetime;

    /**
     *
     * @return
     * The duration
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     * The duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     *
     * @return
     * The risetime
     */
    public Integer getRisetime() {
        return risetime;
    }

    /**
     *
     * @param risetime
     * The risetime
     */
    public void setRisetime(Integer risetime) {
        this.risetime = risetime;
    }

}
