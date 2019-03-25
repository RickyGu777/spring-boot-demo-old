package com.example.servicehi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Alias("UploadImg")
@Getter
@Setter
@ToString
public class UploadImg extends BaseEntity{
    private String originalName;
    private String randomName;
    private String responseUrl;
    private String title;
}