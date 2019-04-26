package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("BaiduOCRWords")
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaiduOCRWords extends BaseEntity {
    private String baiduOCRUUID;
    private String words;
}