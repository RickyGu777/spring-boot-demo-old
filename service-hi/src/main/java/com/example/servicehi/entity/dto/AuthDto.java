package com.example.servicehi.entity.dto;

import com.example.servicehi.entity.Auth;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("AuthDto")
@Getter
@Setter
@ToString
public class AuthDto extends Auth {
    List<Auth> authList;
}