package com.example.servicemiya.Service.Impl;

import com.example.servicemiya.Service.SchedualServiceHi;
import org.springframework.stereotype.Component;

@Component
public class SchedualServiceHiImpl implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
