package com.kvp.kurly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VueForwardController {


    @RequestMapping(value = {"/{path:[^\\\\.]*}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String html5Forwarding(@PathVariable("path") String path) {
        return "forward:/";
    }
}
