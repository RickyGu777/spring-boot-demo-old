package com.example.servicehi.entity;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Alias("WeChatPublicAccountResponseInfo")
@Getter
@Setter
@ToString
public class WeChatPublicAccountResponseInfo extends BaseEntity {
    private String requestInfo;
    private String responseInfo;
}