package com.bbubbush.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth")
public class OauthController {
    @RequestMapping(value = "/main")
    public String oauthMain() {
        return "oauthMain";
    }
}
