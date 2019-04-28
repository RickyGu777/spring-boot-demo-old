package com.example.servicehi.entity;


import lombok.*;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("AhriUrl")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AhriUrl extends BaseEntity implements Serializable {
    private String url;
}