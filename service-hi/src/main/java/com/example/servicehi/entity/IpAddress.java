package com.example.servicehi.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("IpAddress")
@Data
public class IpAddress extends BaseEntity {
    private String ip;
    private String mac;
}