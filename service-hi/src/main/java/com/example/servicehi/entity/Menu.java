package com.example.servicehi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("Menu")
@Getter
@Setter
@ToString
public class Menu extends BaseEntity{
    private String menuName;
    private String menuPath;
    private String parentMenuUUID;
    private String index;
    private String type;
    private List<Menu> nextMenuList;
}