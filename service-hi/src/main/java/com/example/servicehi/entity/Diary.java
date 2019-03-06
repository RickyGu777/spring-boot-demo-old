package com.example.servicehi.entity;

import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("Diary")
@Data
@ToString
public class Diary extends BaseEntity{
    private String title;
    private String text;
    private List<String> tips;
}