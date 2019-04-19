package com.example.servicehi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("DiaryHistory")
@Getter
@Setter
@ToString
public class DiaryHistory extends BaseEntity{
    private String diaryUUID;
    private String title;
    private String historyText;
    private Integer modiIndex;
}