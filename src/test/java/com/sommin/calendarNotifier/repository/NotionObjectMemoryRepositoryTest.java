package com.sommin.calendarNotifier.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class NotionObjectMemoryRepositoryTest {

    @Autowired
    NotionRepository notionRepository;

    @Test
    void getNotionDatabaseIdByNameTest() {
        String r = notionRepository.getNotionDatabaseIdByName("회의/출장/휴가");
        assertThat(r).isEqualTo("d52b0de994134e2d9198d8ee4cfc01ed");
    }

    @Test
    void getNotionUserNameById() {
        String r = notionRepository.getNotionUserNameById("id1");
        assertThat(r).isEqualTo("허성민");
    }
}
