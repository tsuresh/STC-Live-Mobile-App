package com.stcml.live.youtube;

public class VideoItem {

    String _title;
    String _vid;
    String _pubDate;
    String _thumbURL;

    public VideoItem(){

    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public String getVID() {
        return _vid;
    }

    public void setVID(String vid) {
        this._vid = vid;
    }

    public String getPubDate() {
        return _pubDate;
    }

    public void setPubDate(String pubDate) {
        this._pubDate = pubDate;
    }

    public String getThumbURL() {
        return _thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this._thumbURL = thumbURL;
    }
}
