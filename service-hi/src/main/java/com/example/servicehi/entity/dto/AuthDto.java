package com.example.servicehi.entity.dto;

import com.example.servicehi.entity.Auth;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("AuthDto")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto extends Auth {
    List<Auth> authList;
}