package com.example.servicehi.entity;

import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("Joker")
@Data
@ToString
public class Joke extends BaseEntity{
    private String title;
    private String joke;
    private String from;
    private String remark;
}