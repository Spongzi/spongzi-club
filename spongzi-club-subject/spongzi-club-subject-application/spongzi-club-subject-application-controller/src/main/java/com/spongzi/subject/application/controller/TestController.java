package com.spongzi.subject.application.controller;

import com.spongzi.subject.infra.basic.service.SubjectEsService;
import com.spongzi.subject.infra.entity.UserInfo;
import com.spongzi.subject.infra.rpc.UserRpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试控制器
 *
 * @author spong
 * @date 2023/12/05
 */
@Slf4j
@RestController
@RequestMapping("/subject/test")
public class TestController {

    @Resource
    private UserRpc userRpc;

    @Resource
    private SubjectEsService subjectEsService;

    @GetMapping("/testFeign")
    public void testFeign() {
        UserInfo userInfo = userRpc.getUserInfo("spongzi");
        log.info("testFeign.userInfo: {}", userInfo);
    }

}
