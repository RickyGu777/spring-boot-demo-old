package com.example.servicehi.entity.dto;

import com.example.servicehi.entity.BaiduOCR;
import com.example.servicehi.entity.BaiduOCRWords;
import com.example.servicehi.entity.UploadImg;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("BaiduOCRDto")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaiduOCRDto extends BaiduOCR {
    private List<BaiduOCRWords> wordsResult;
    private UploadImg uploadImg;
}