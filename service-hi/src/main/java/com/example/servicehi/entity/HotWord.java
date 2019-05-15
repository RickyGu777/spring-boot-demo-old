package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("HotWord")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class HotWord extends BaseEntity{
    private String hotWord;
    private int times;
}