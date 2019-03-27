package com.example.servicehi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("Tips")
@Getter
@Setter
@ToString
public class Tips extends BaseEntity {
    private String name;
}