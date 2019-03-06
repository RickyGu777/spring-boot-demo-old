package com.example.servicehi.entity;

import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("TipsRelation")
@Data
@ToString
public class TipsRelation extends BaseEntity{
    private String tipsUUID;
    private String otherUUID;
}