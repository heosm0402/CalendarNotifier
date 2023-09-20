package com.sommin.calendarNotifier.service;

import com.google.gson.Gson;
import com.sommin.calendarNotifier.domain.NotionMemberObject;
import org.json.JSONArray;

import java.time.LocalDate;
import java.util.ArrayList;

public class NotionService {
    private final NotionClient notionClient;
    private final Gson gson = new Gson();

    public NotionService(NotionClient notionClient) {
        this.notionClient = notionClient;
    }

    public void extractCalendarData(LocalDate localDate) {
        System.out.println("local date: " + localDate);
        notionClient.extractDatabase("회의/출장/휴가");

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
