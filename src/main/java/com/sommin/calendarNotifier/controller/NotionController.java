package com.sommin.calendarNotifier.controller;

import com.sommin.calendarNotifier.domain.NotionMemberObject;
import com.sommin.calendarNotifier.domain.NotionPageObject;
import com.sommin.calendarNotifier.http.ApiResponse;
import com.sommin.calendarNotifier.service.NotionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.Charset;
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
    public ResponseEntity<ApiResponse> notionController() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setMessage("api for notion");

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(apiResponse, headers, HttpStatus.OK);
    }

    @GetMapping("/extract/{datetime}")
    public ResponseEntity<ApiResponse> extractNotionCalendarData(@PathVariable("datetime") String datetime) {
//        LocalDate localDate = LocalDate.parse(datetime, DateTimeFormatter.ISO_DATE);
        ArrayList<NotionPageObject> calendarPageDataArrayList = notionService.extractCalendarData(datetime);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setMessage("Extract of notion calendar data " + datetime +  " is requested");
        apiResponse.setData(calendarPageDataArrayList);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(apiResponse, headers, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse> getAllUsers() {
        ArrayList<NotionMemberObject> result = notionService.listAllUserInfo();

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setMessage("list all users");
        apiResponse.setData(result);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(apiResponse, headers, HttpStatus.OK);
    }
}
