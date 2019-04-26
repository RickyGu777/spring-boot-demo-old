package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("User")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{
    private String userCode;
    private String userName;
    private String password;
    private String mail;
    private String phone;
    private String wxOpenid;
    private String qqOpenid;
}