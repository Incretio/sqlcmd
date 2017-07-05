package ru.incretio.juja.sqlcmd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {
        return "help";
    }
}
