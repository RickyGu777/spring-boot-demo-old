package com.example.servicehi.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

@Alias("AhriUrl")
@Getter
@Setter
@ToString
@JsonInclude(Include.NON_NULL)
public class AhriUrl extends BaseEntity implements Serializable {
    private String url;
}