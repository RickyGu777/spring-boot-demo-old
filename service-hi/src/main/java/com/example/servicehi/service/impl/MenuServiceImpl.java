package com.example.servicehi.service.impl;

import com.example.servicehi.dao.MenuDao;
import com.example.servicehi.entity.Menu;
import com.example.servicehi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl<T extends Menu> implements MenuService<T> {
    @Autowired
    private MenuDao<T> menuDao;

    @Override
    public List<T> selectMenu(T t) {
        return menuDao.selectMenu(t);
    }
}
