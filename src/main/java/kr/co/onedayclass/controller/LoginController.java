package kr.co.onedayclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginP(){
        return "login";
    }
    @GetMapping("/admin")
    public String adminP(){
        return "admin";
    }
}
