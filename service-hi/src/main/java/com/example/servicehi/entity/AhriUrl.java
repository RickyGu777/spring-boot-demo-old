package com.example.servicehi.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("AhriUrl")
@Getter
@Setter
@ToString
public class AhriUrl extends BaseEntity implements Serializable {
    private String url;
}