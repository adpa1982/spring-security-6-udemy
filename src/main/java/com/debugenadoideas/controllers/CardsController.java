package com.debugenadoideas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author Alberto Puche Algarin
 * @version 1.0
 * @description CardsController
 * @date
 */
@RestController
@RequestMapping(path = "/cards")
public class CardsController {

    @GetMapping("/")
    public Map<String, String> cards() {
        return Collections.singletonMap("msj", "cards");
    }
}
