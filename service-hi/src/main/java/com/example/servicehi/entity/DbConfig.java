package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("DbConfig")
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DbConfig extends BaseEntity{
    private String code;
    private String value;
    private String remark;
}