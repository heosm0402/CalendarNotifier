package com.sommin.calendarNotifier.domain;

import com.google.gson.JsonArray;

import java.util.ArrayList;

public class NotionPageObject {
    String objectId;
    String url;
    String startDate;
    String endDate;
    ArrayList<NotionMemberObject> people;
    String pageTitle;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<NotionMemberObject> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<NotionMemberObject> people) {
        this.people = people;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
}
