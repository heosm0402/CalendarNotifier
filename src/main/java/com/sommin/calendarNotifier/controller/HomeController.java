package com.sommin.calendarNotifier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@ResponseBody
public class HomeController {

    @GetMapping("/")
    public HashMap<String, String> home() {
        HashMap<String, String> map = new HashMap<>();
        map.put("msg", "home");
        return map;
    }
}
