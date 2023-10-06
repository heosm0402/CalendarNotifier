package com.sommin.calendarNotifier.repository;

import java.util.HashMap;
import java.util.Map;

public class NotionObjectMemoryRepository implements NotionRepository {
    private final Map<String, String> databaseMapper;
    private final Map<String, String> userMapper;


    public NotionObjectMemoryRepository() {
        this.databaseMapper = new HashMap<>();
        databaseMapper.put("db1", System.getenv("db1"));
        databaseMapper.put("db2", System.getenv("db2"));
        databaseMapper.put("calendarDB", System.getenv("calendarDB"));

        this.userMapper = new HashMap<>();
        userMapper.put("user1", System.getenv("user1"));
        userMapper.put("user2", System.getenv("user2"));
    }

    @Override
    public String getNotionDatabaseIdByName(String databaseName) {
        return databaseMapper.get(databaseName);
    }

    @Override
    public String getNotionUserNameById(String userId) {
        return userMapper.get(userId);
    }
}
