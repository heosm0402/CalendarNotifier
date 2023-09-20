package com.sommin.calendarNotifier.service;

import com.sommin.calendarNotifier.domain.NotionMemberObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class NotionServiceTest {

    @Autowired
    NotionService notionService;

    @Test
    void listAllUserInfoSizeCheck() {
        ArrayList<NotionMemberObject> arrayList = notionService.listAllUserInfo();
        assertThat(arrayList.size()).isEqualTo(25);
    }

    @Test
    void listAllUserInfoTypecheck() {
        ArrayList<NotionMemberObject> arrayList = notionService.listAllUserInfo();
        assertThat(arrayList.get(0).getClass().getName()).isEqualTo("com.sommin.calendarNotifier.domain.NotionMemberObject");
    }
}
