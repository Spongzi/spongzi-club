package com.spongzi.wx.controller;

import com.spongzi.wx.handler.WxChatMsgFactory;
import com.spongzi.wx.handler.WxChatMsgHandler;
import com.spongzi.wx.utils.MessageUtil;
import com.spongzi.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * 回叫控制器
 *
 * @author spong
 * @date 2023/11/06
 */
@Slf4j
@RestController
public class CallBackController {

    private static final String token = "spongzi";

    @Resource
    private WxChatMsgFactory wxChatMsgFactory;

    @GetMapping("/test")
    public String test() {
        return "hello wx";
    }

    /**
     * 回调校验
     *
     * @param signature 签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   回声
     * @return {@link String}
     */
    @GetMapping("callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        log.info("get验签请求参数：signature: {}, timestamp: {}, nonce: {}, echostr: {}", signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        if (signature.equals(shaStr)) {
            return echostr;
        }
        return "unknown";
    }

    /**
     * 回调校验
     *
     * @param signature 签名
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @return {@link String}
     */
    @PostMapping(value = "callback", produces = "application/xml;charset=utf-8")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false) String msgSignature
    ) {
        log.info("接收到微信的请求：requestBody: {}, signature: {}, timestamp: {}, nonce: {}", requestBody, signature, timestamp, nonce);
        Map<String, String> messageMap = MessageUtil.parseXml(requestBody);
        String msgType = messageMap.get("MsgType");
        String event = messageMap.get("Event") == null ? "" : messageMap.get("Event");
        log.info("msgType:{},event:{}", msgType, event);

        StringBuilder sb = new StringBuilder();
        sb.append(msgType);
        if (!StringUtils.isEmpty(event)) {
            sb.append(".");
            sb.append(event);
        }
        String msgTypeKey = sb.toString();
        log.info("msgTypeKey: {}", msgTypeKey);
        WxChatMsgHandler wxChatMsgHandler = wxChatMsgFactory.getHandlerByMsgType(msgTypeKey);
        if (Objects.isNull(wxChatMsgHandler)) {
            return "unknown";
        }
        String replyContent = wxChatMsgHandler.dealMsg(messageMap);
        log.info("replyContent:{}", replyContent);
        return replyContent;
    }
}
