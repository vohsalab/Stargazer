package com.firstsputnik.stargazer.Model;

/**
 * Created by ibalashov on 3/20/2016.
 */import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ISSPasses {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("request")
    @Expose
    private Request request;
    @SerializedName("response")
    @Expose
    private List<ISSPassesResponse> response = new ArrayList<ISSPassesResponse>();

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The request
     */
    public Request getRequest() {
        return request;
    }

    /**
     *
     * @param request
     * The request
     */
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     *
     * @return
     * The response
     */
    public List<ISSPassesResponse> getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    public void setResponse(List<ISSPassesResponse> response) {
        this.response = response;
    }

}
