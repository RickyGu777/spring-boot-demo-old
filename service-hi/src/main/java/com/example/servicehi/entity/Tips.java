package com.example.servicehi.entity;

import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("Tips")
@Data
@ToString
public class Tips extends BaseEntity {
    private String name;
    private Integer times;
}