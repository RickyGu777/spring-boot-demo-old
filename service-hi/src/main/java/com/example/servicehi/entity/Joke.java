package com.example.servicehi.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("Joke")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Joke extends BaseEntity{
    private String title;
    private String joke;
    private String from;
    private String remark;
}