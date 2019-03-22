package com.example.servicehi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("BaiduOCRWords")
@ToString
@Getter
@Setter
public class BaiduOCRWords extends BaseEntity {
    private String baiduOCRUUID;
    private String words;
}