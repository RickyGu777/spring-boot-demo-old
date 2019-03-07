package com.example.servicehi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("TipsRelation")
@Getter
@Setter
@ToString
public class TipsRelation extends BaseEntity{
    private String tipsUUID;
    private String otherUUID;
}