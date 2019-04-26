package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("UserMenuRelation")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserMenuRelation extends BaseEntity {
    private String menuUUID;
    private String menuName;
    private String menuPath;
    private String parentMenuUUID;
    private String index;
    private String userUUID;
    private String defaultCheck;
}