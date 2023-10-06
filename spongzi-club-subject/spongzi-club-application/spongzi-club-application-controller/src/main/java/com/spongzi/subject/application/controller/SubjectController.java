package com.spongzi.subject.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 刷题controller
 *
 * @author spong
 * @date 2023/10/06
 */
@RestController
public class SubjectController {

    @GetMapping("/test")
    public String test() {
        return "hello club";
    }
}
