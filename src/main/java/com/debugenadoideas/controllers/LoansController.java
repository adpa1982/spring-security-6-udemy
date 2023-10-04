package com.debugenadoideas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author Alberto Puche Algarin
 * @version 1.0
 * @description LoansController
 * @date
 */
@RestController
@RequestMapping(path = "/loans")
public class LoansController {

    @GetMapping("/")
    public Map<String, String> loans() {
        return Collections.singletonMap("msj", "loans");
    }
}
