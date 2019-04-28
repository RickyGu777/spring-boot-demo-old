package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("UserAuthRelation")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthRelation extends BaseEntity{
    private String authUUID;
    private String authName;
    private String userUUID;
    private String defaultCheck;
}
