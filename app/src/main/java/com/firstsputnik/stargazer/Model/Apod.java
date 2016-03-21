package com.firstsputnik.stargazer.Model;

/**
 * Created by ibalashov on 3/16/2016.
 */

    import com.google.gson.annotations.Expose;
    import com.google.gson.annotations.SerializedName;

    public class Apod {

        @SerializedName("copyright")
        @Expose
        private String copyright;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("explanation")
        @Expose
        private String explanation;
        @SerializedName("hdurl")
        @Expose
        private String hdurl;
        @SerializedName("media_type")
        @Expose
        private String mediaType;
        @SerializedName("service_version")
        @Expose
        private String serviceVersion;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("url")
        @Expose
        private String url;

        /**
         *
         * @return
         * The copyright
         */
        public String getCopyright() {
            return copyright;
        }

        /**
         *
         * @param copyright
         * The copyright
         */
        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        /**
         *
         * @return
         * The date
         */
        public String getDate() {
            return date;
        }

        /**
         *
         * @param date
         * The date
         */
        public void setDate(String date) {
            this.date = date;
        }

        /**
         *
         * @return
         * The explanation
         */
        public String getExplanation() {
            return explanation;
        }

        /**
         *
         * @param explanation
         * The explanation
         */
        public void setExplanation(String explanation) {
            this.explanation = explanation;
        }

        /**
         *
         * @return
         * The hdurl
         */
        public String getHdurl() {
            return hdurl;
        }

        /**
         *
         * @param hdurl
         * The hdurl
         */
        public void setHdurl(String hdurl) {
            this.hdurl = hdurl;
        }

        /**
         *
         * @return
         * The mediaType
         */
        public String getMediaType() {
            return mediaType;
        }

        /**
         *
         * @param mediaType
         * The media_type
         */
        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        /**
         *
         * @return
         * The serviceVersion
         */
        public String getServiceVersion() {
            return serviceVersion;
        }

        /**
         *
         * @param serviceVersion
         * The service_version
         */
        public void setServiceVersion(String serviceVersion) {
            this.serviceVersion = serviceVersion;
        }

        /**
         *
         * @return
         * The title
         */
        public String getTitle() {
            return title;
        }

        /**
         *
         * @param title
         * The title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         *
         * @return
         * The url
         */
        public String getUrl() {
            return url;
        }

        /**
         *
         * @param url
         * The url
         */
        public void setUrl(String url) {
            this.url = url;
        }

    }

