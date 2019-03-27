package com.example.servicehi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("ShareTicketUrl")
@Getter
@Setter
@ToString
public class ShareTicketUrl extends BaseEntity{
    private String url;
    private String title;
    private String remark;
}