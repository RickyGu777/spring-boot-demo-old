package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("DiaryHistory")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DiaryHistory extends BaseEntity{
    private String diaryUUID;
    private String title;
    private String historyText;
    private Integer modiIndex;
}