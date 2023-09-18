package com.sommin.calendarNotifier;

import com.sommin.calendarNotifier.service.NotionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public NotionService notionService() {
        return new NotionService();
    }

}
