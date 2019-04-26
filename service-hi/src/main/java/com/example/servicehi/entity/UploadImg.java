package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("UploadImg")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UploadImg extends BaseEntity{
    private String originalName;
    private String randomName;
    private String responseUrl;
    private String title;
    private String imagePath;
}