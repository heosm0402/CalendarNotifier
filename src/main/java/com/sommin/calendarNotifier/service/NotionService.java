package com.sommin.calendarNotifier.service;

import com.google.gson.*;
import com.sommin.calendarNotifier.domain.NotionMemberObject;
import com.sommin.calendarNotifier.domain.NotionPageObject;
import org.json.JSONArray;

import java.util.ArrayList;

public class NotionService {
    private final String CALENDAR_DB = "calendarDB";
    private final NotionClient notionClient;
    private final Gson gson = new Gson();

    public NotionService(NotionClient notionClient) {
        this.notionClient = notionClient;
    }

    public ArrayList<NotionPageObject> extractCalendarData(String localDate) {
        System.out.println("local date: " + localDate);
        String result = notionClient.extractDatabase(CALENDAR_DB, localDate);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(result).getAsJsonObject();
        JsonArray jsonArrayForEventNotionPage = jsonObject.get("results").getAsJsonArray();
        ArrayList<NotionPageObject> calendarPageDataArrayList = new ArrayList<>();
        for (JsonElement eventNotionPage : jsonArrayForEventNotionPage ) {
            NotionPageObject notionPageObject = extractNotionPageObjectFromJsonElement(eventNotionPage);
            calendarPageDataArrayList.add(notionPageObject);
        }
        return calendarPageDataArrayList;
    }

    private NotionPageObject extractNotionPageObjectFromJsonElement(JsonElement notionPage) {
        NotionPageObject notionPageObject = new NotionPageObject();

        // set page url, id
        JsonObject eventNotionPageJson = notionPage.getAsJsonObject();
        notionPageObject.setUrl(eventNotionPageJson.get("url").getAsString());
        notionPageObject.setObjectId(eventNotionPageJson.get("id").getAsString());

        // get page properties
        JsonObject notionPageProperties = eventNotionPageJson.get("properties").getAsJsonObject();

        // set page people
        boolean isPeopleNull = notionPageProperties.get("대상자").getAsJsonObject().get("people").isJsonNull();
        if (!isPeopleNull){
            JsonArray peopleJsonArray = notionPageProperties.get("대상자").getAsJsonObject().get("people").getAsJsonArray();
            ArrayList<NotionMemberObject> peopleArrayList = new ArrayList<>();
            for (JsonElement peopleObject : peopleJsonArray) {
                JsonObject peopleJson = peopleObject.getAsJsonObject();
                peopleArrayList.add(gson.fromJson(peopleJson.toString(), NotionMemberObject.class));
            }
            notionPageObject.setPeople(peopleArrayList);
        }

        // set page title
        boolean isTitleNull = notionPageProperties.get("이름").getAsJsonObject().get("title").getAsJsonArray().get(0).getAsJsonObject().get("plain_text").isJsonNull();
        if (!isTitleNull) {
            notionPageObject.setPageTitle(notionPageProperties.get("이름").getAsJsonObject().get("title").getAsJsonArray().get(0).getAsJsonObject().get("plain_text").getAsString());
        }

        // set page start date
        boolean isStartdateNull = notionPageProperties.get("날짜").getAsJsonObject().get("date").getAsJsonObject().get("start").isJsonNull();
        if (!isStartdateNull) {
            notionPageObject.setStartDate(notionPageProperties.get("날짜").getAsJsonObject().get("date").getAsJsonObject().get("start").getAsString());
        }

        // set page end date
        boolean isEnddateNull = notionPageProperties.get("날짜").getAsJsonObject().get("date").getAsJsonObject().get("end").isJsonNull();
        if (!isEnddateNull) {
            notionPageObject.setEndDate(notionPageProperties.get("날짜").getAsJsonObject().get("date").getAsJsonObject().get("end").getAsString());
        }

        return notionPageObject;
    }

    public ArrayList<NotionMemberObject> listAllUserInfo() {
        JSONArray r = notionClient.listAllUsers();
        ArrayList<NotionMemberObject> notionMemberObjectArrayList = new ArrayList<>();
        for (Object memberObject : r) {
            notionMemberObjectArrayList.add(gson.fromJson(memberObject.toString(), NotionMemberObject.class));
        }

        return notionMemberObjectArrayList;
    }


}
