package com.debugenadoideas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author Alberto Puche Algarin
 * @version 1.0
 * @description AboutUsController
 * @date
 */
@RestController
@RequestMapping(path = "/about_us")
public class AboutUsController {

    @GetMapping("/")
    public Map<String, String> about() {
        return Collections.singletonMap("msj", "about");
    }
}
