package com.project.test.apptest.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class NotificationModel {
    
    @SerializedName("id")
    private String id;
    @SerializedName("subject")
    private String subject;
    @SerializedName("text")
    private String text;
    @SerializedName("startDateTime")
    private Date startDateTime;
    @SerializedName("endDateTime")
    private Date endDateTime;
    
    public String getSubject() {
        return subject;
    }
    
    public String getText() {
        return text;
    }
    
    public Date getStartDateTime() {
        return startDateTime;
    }
    
    public Date getEndDateTime() {
        return endDateTime;
    }
}
