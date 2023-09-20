package com.sommin.calendarNotifier.controller;

import com.sommin.calendarNotifier.domain.DefaultResponse;
import com.sommin.calendarNotifier.domain.NotionMemberObject;
import com.sommin.calendarNotifier.service.NotionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Controller
@RequestMapping("/notion")
@ResponseBody
public class NotionController {

    private final NotionService notionService;

    public NotionController(NotionService notionService) {
        this.notionService = notionService;
    }

    @GetMapping("")
    public DefaultResponse notionController() {
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setStatus(HttpStatus.OK);
        defaultResponse.setMsg("notion controller root");
        return defaultResponse;
    }

    @GetMapping("/extract/{datetime}")
    public DefaultResponse extractNotionCalendarData(@PathVariable("datetime") String datetime) {
        LocalDate localDate = LocalDate.parse(datetime, DateTimeFormatter.ISO_DATE);
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setStatus(HttpStatus.OK);
        defaultResponse.setMsg("Extract of notion calendar data " + datetime +  " is requested");
        notionService.extractCalendarData(localDate);

        return defaultResponse;
    }

    @GetMapping("/users")
    public DefaultResponse getAllUsers() {
        ArrayList<NotionMemberObject> result = notionService.listAllUserInfo();
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setStatus(HttpStatus.OK);
        defaultResponse.setMsg("list all users");
        defaultResponse.setPayload(result);
        return defaultResponse;
    }
}
