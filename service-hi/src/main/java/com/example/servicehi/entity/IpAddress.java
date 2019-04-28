package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("IpAddress")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class IpAddress extends BaseEntity {
    private String ip;
    private String mac;
}