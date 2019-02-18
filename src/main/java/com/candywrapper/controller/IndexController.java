package com.candywrapper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class IndexController {
    @GetMapping(value = "/")
    public String index() {
        return "index.html";
    }
}