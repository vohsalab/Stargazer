package com.firstsputnik.stargazer.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Request {

    @SerializedName("altitude")
    @Expose
    private Integer altitude;
    @SerializedName("datetime")
    @Expose
    private Integer datetime;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("passes")
    @Expose
    private Integer passes;

    /**
     *
     * @return
     * The altitude
     */
    public Integer getAltitude() {
        return altitude;
    }

    /**
     *
     * @param altitude
     * The altitude
     */
    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    /**
     *
     * @return
     * The datetime
     */
    public Integer getDatetime() {
        return datetime;
    }

    /**
     *
     * @param datetime
     * The datetime
     */
    public void setDatetime(Integer datetime) {
        this.datetime = datetime;
    }

    /**
     *
     * @return
     * The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The passes
     */
    public Integer getPasses() {
        return passes;
    }

    /**
     *
     * @param passes
     * The passes
     */
    public void setPasses(Integer passes) {
        this.passes = passes;
    }

}