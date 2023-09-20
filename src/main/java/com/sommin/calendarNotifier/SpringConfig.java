package com.sommin.calendarNotifier;

import com.sommin.calendarNotifier.repository.NotionObjectMemoryRepository;
import com.sommin.calendarNotifier.repository.NotionRepository;
import com.sommin.calendarNotifier.service.NotionClient;
import com.sommin.calendarNotifier.service.NotionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public NotionClient notionClient() {
        return new NotionClient(notionRepository());
    }
    @Bean
    public NotionService notionService() {
        return new NotionService(notionClient());
    }
    @Bean
    public NotionRepository notionRepository() {
        return new NotionObjectMemoryRepository();
    }

}
