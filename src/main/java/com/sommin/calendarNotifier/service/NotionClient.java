package com.sommin.calendarNotifier.service;

import com.sommin.calendarNotifier.repository.NotionRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class NotionClient {
    private final String NOTION_BASE_URL = "https://api.notion.com/v1/";
    private final String NOTION_TOKEN = System.getenv("NOTION_TOKEN");
    private final String NOTION_VERSION = System.getenv("NOTION_VERSION");

    private final NotionRepository notionRepository;

    public NotionClient(NotionRepository notionRepository) {
        this.notionRepository = notionRepository;
    }

    public String extractDatabase(String databaseName, String isoDate) {
        String databaseId = notionRepository.getNotionDatabaseIdByName(databaseName);

        JSONObject dateMap = new JSONObject();
        dateMap.put("after", isoDate);

        JSONObject filterMap = new JSONObject();
        filterMap.put("property", "날짜");
        filterMap.put("date", dateMap);

        JSONObject body = new JSONObject();
        body.put("filter", filterMap);

        HttpHeaders headers = getHeader();
        HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);
        RestTemplate rt = new RestTemplate();
        String apiEndPoint = NOTION_BASE_URL + "databases/" + databaseId + "/" + "query";

        ResponseEntity<String> response = rt.exchange(apiEndPoint, HttpMethod.POST, entity, String.class);
        return response.getBody();
    }

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + NOTION_TOKEN);
        headers.add("Content-Type", "application/json");
        headers.add("Notion-Version", NOTION_VERSION);

        return headers;
    }

    public JSONArray listAllUsers() {
        HttpHeaders headers = getHeader();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(parameters, headers);
        String apiEndPoint = NOTION_BASE_URL + "users/";
        RestTemplate rt = new RestTemplate();

        ResponseEntity<String> response = rt.exchange(apiEndPoint, HttpMethod.GET, entity, String.class);
        String result = response.getBody();
        return new JSONObject(result).getJSONArray("results");
    }
}
