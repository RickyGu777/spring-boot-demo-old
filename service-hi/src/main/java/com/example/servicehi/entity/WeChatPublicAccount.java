package com.example.servicehi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("WeChatPublicAccount")
@Getter
@Setter
@ToString
public class WeChatPublicAccount extends BaseEntity {
    private String userCode;
    private String userName;
    private String userUUID;
    private String WxOpenid;
    private String userSendInfo;
    private Integer responseInfoId;
    private String responseInfo;
    private String messageId;
}