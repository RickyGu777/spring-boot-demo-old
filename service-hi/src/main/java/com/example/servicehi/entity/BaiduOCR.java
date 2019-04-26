package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("BaiduOCR")
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaiduOCR extends BaseEntity {
    private String logId;
    private String uploadImgUUID;
    private Integer wordsResultNum;
    private String errorCode;
    private String errorMsg;
}