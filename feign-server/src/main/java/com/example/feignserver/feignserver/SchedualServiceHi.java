package com.example.feignserver.feignserver;

import com.example.feignserver.util.ResponseUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@FeignClient(value = "service-hi")
public interface SchedualServiceHi {

    @RequestMapping(value = "/Menu/getMenuList", method = RequestMethod.POST)
    ResponseUtil<List<Map>> getMenuList(@RequestBody(required = false) Map menu);
}
