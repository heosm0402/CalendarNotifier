package com.sommin.calendarNotifier.repository;

public interface NotionRepository {
    String getNotionDatabaseIdByName(String databaseId);
    String getNotionUserNameById(String userId);

}
