package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("TipsRelation")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TipsRelation extends BaseEntity{
    private String tipsUUID;
    private String tipsName;
    private String otherUUID;
}