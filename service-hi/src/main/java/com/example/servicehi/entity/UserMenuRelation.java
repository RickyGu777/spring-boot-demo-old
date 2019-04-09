package com.example.servicehi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("UserMenuRelation")
@Getter
@Setter
@ToString
public class UserMenuRelation extends BaseEntity {
    private String menuUUID;
    private String menuName;
    private String menuPath;
    private String parentMenuUUID;
    private String index;
    private String userUUID;
}