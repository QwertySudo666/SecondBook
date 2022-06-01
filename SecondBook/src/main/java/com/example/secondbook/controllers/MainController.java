package com.example.secondbook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/main-page")
    public String getMainPage() {
        return "main-page";
    }
}
