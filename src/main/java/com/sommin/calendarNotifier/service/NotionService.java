package com.sommin.calendarNotifier.service;

import com.google.gson.*;
import com.sommin.calendarNotifier.domain.NotionMemberObject;
import com.sommin.calendarNotifier.domain.NotionPageObject;
import org.json.JSONArray;

import java.time.LocalDate;
import java.util.ArrayList;

public class NotionService {
    private final NotionClient notionClient;
    private final Gson gson = new Gson();

    public NotionService(NotionClient notionClient) {
        this.notionClient = notionClient;
    }

    public ArrayList<NotionPageObject> extractCalendarData(LocalDate localDate) {
        System.out.println("local date: " + localDate);
        String result = notionClient.extractDatabase("회의/출장/휴가");
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(result).getAsJsonObject();
        JsonArray jsonArrayForEventNotionPage = jsonObject.get("results").getAsJsonArray();
        ArrayList<NotionPageObject> calendarPageDataArrayList = new ArrayList<>();
        int i = 0;
        for (JsonElement eventNotionPage : jsonArrayForEventNotionPage ) {
            JsonObject eventNotionPageJson = eventNotionPage.getAsJsonObject();

            NotionPageObject notionPageObject = new NotionPageObject();
            notionPageObject.setUrl(eventNotionPageJson.get("url").getAsString());
            notionPageObject.setObjectId(eventNotionPageJson.get("id").getAsString());

            JsonObject notionPageProperties = eventNotionPageJson.get("properties").getAsJsonObject();
            notionPageObject.setPageTitle(notionPageProperties.get("이름").getAsJsonObject().get("title").getAsJsonArray().get(0).getAsJsonObject().get("plain_text").getAsString());
            JsonArray peopleJsonArray = notionPageProperties.get("대상자").getAsJsonObject().get("people").getAsJsonArray();
            ArrayList<NotionMemberObject> peopleArrayList = new ArrayList<>();
            for (JsonElement peopleObject : peopleJsonArray) {
                JsonObject peopleJson = peopleObject.getAsJsonObject();
                peopleArrayList.add(gson.fromJson(peopleJson.toString(), NotionMemberObject.class));
            }
            notionPageObject.setPeople(peopleArrayList);
//            notionPageObject.setPeople(notionPageProperties.get("대상자").getAsJsonObject().get("people").getAsJsonArray());
            notionPageObject.setStartDate(notionPageProperties.get("날짜").getAsJsonObject().get("date").getAsJsonObject().get("start").getAsString());
            Boolean end = notionPageProperties.get("날짜").getAsJsonObject().get("date").getAsJsonObject().get("end").isJsonNull();
            if (!end) {
                notionPageObject.setEndDate(notionPageProperties.get("날짜").getAsJsonObject().get("date").getAsJsonObject().get("end").getAsString());
            }
            calendarPageDataArrayList.add(notionPageObject);
        }
        return calendarPageDataArrayList;
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
