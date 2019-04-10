package com.example.servicehi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("UserAuthRelation")
@Getter
@Setter
@ToString
public class UserAuthRelation extends BaseEntity{
    private String authUUID;
    private String authName;
    private String userUUID;
    private String defaultCheck;
}
