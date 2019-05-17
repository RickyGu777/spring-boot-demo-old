package com.example.wechat.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("WeChatPublicAccountResponseInfo")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class WeChatPublicAccountResponseInfo extends BaseEntity {
    private String requestInfo;
    private String responseInfo;
}