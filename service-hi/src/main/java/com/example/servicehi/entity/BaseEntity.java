package com.example.servicehi.entity;


import com.example.servicehi.util.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Slf4j
public class BaseEntity implements Serializable {
    private String uuid;
    private String createUserUUID;
    private Date createDate;
    private String modiUserUUID;
    private Date modiDate;
    private String isDel;
    private String status;

    public BaseEntity() {
        if (this.uuid == null) {
            this.uuid = UUIDUtil.getUUID();
            log.info("CREATE UUID ---> " + this.uuid);
        }
    }
}