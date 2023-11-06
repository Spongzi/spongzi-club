package com.spongzi.wx.handler;

import java.util.Map;

/**
 * WX聊天消息处理程序
 *
 * @author spong
 * @date 2023/11/06
 */
public interface WxChatMsgHandler {

    WxChatMsgTypeEnum getMsgType();

    String dealMsg(Map<String, String> messageMap);

}
