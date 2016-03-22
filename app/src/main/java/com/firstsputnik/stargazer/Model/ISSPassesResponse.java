package com.firstsputnik.stargazer.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ISSPassesResponse {

    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("risetime")
    @Expose
    private Long risetime;

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
    public Long getRisetime() {
        return risetime;
    }

    /**
     *
     * @param risetime
     * The risetime
     */
    public void setRisetime(Long risetime) {
        this.risetime = risetime;
    }

}
