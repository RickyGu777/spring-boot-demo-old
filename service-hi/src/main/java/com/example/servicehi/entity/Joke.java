package com.example.servicehi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("Joker")
@Getter
@Setter
@ToString
public class Joke extends BaseEntity{
    private String title;
    private String joke;
    private String from;
    private String remark;
}