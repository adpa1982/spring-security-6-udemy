package com.debugenadoideas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author Alberto Puche Algarin
 * @version 1.0
 * @description AccountsController
 * @date
 */
@RestController
@RequestMapping(path = "/accounts")
public class AccountsController {

    @GetMapping("/")
    public Map<String, String> accounts() {
        return Collections.singletonMap("msj", "accounts");
    }
}
