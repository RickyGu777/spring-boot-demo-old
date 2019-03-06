package com.example.servicehi.entity;

import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("IpAddress")
@Data
@ToString
public class IpAddress extends BaseEntity {
    private String ip;
    private String mac;
}