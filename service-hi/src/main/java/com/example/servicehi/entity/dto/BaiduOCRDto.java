package com.example.servicehi.entity.dto;

import com.example.servicehi.entity.Auth;
import com.example.servicehi.entity.BaiduOCR;
import com.example.servicehi.entity.BaiduOCRWords;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("BaiduOCRDto")
@Getter
@Setter
@ToString
public class BaiduOCRDto extends BaiduOCR {
    List<BaiduOCRWords> wordsResult;
}