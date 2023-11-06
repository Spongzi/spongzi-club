package com.spongzi.wx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 回叫控制器
 *
 * @author spong
 * @date 2023/11/06
 */
@RestController
public class CallBackController {

    @GetMapping("/test")
    public String test() {
        return "hello wx";
    }

    
}
