package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("IpRecord")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class IpRecord extends BaseEntity{
    private String ipAddress;
    private Date date;
    private Integer times;
}