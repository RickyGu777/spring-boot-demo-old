package com.example.servicehi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("Diary")
@Getter
@Setter
@ToString
public class Diary extends BaseEntity{
    private String title;
    private String text;
    private List<String> tips;
}