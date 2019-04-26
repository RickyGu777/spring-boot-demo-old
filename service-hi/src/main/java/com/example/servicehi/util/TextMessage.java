package com.example.servicehi.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextMessage {
    private String fromUserName;
    private String toUserName;
    private String msgType;
    private Long createTime;
    private String content;
}
