package com.example.servicehi.entity;


import com.example.servicehi.util.UUIDUtil;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Slf4j
public class BaseEntity implements Serializable {
    private Integer id;
    private String uuid;
    private String createUserUUID;
    private Date createDate;
    private String modiUserUUID;
    private Date modiDate;
    private String isDel;
    private String status;
    private Integer page;
    private Integer size;

    public BaseEntity() {
        if (this.uuid == null) {
            this.uuid = UUIDUtil.createUUID();
            log.info("CREATE UUID ---> " + this.uuid);
        }
    }
}