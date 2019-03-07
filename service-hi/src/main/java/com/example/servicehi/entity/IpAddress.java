package com.example.servicehi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("IpAddress")
@Getter
@Setter
@ToString
public class IpAddress extends BaseEntity {
    private String ip;
    private String mac;
}