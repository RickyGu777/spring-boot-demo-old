package com.example.feignserver.feignserver.fallback;

import com.example.feignserver.feignserver.SchedualServiceHi;
import com.example.feignserver.util.ResponseUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
public class SchedualServiceHiImpl implements SchedualServiceHi {
    @Setter
    private Throwable cause;

    @Override
    public ResponseUtil<List<Map>> getMenuList(Map menu) {
        log.error("获取详细信息失败,原因:{}", cause.getMessage(), cause);
        return new ResponseUtil(cause);
    }
}