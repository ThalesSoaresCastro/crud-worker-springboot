package com.thales.workerapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class DocumentController {
    
    @RequestMapping("/docs")
    public String documentation(){
        return "redirect:/swagger-ui.html";
    }
}