package com.example.servicehi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Objects;

@Alias("BaiduOCR")
@ToString
@Getter
@Setter
public class BaiduOCR extends BaseEntity {
    private String logId;
    private String uploadImgUUID;
    private Integer wordsResultNum;
}