package com.example.feignserver.feignserver.factory;

import com.example.feignserver.feignserver.SchedualServiceHi;
import com.example.feignserver.feignserver.fallback.SchedualServiceHiImpl;
import feign.hystrix.FallbackFactory;

public class SchedualServiceHiFallbackFactory implements FallbackFactory<SchedualServiceHi> {
    @Override
    public SchedualServiceHi create(Throwable throwable) {
        SchedualServiceHiImpl schedualServiceHi = new SchedualServiceHiImpl();
        schedualServiceHi.setCause(throwable);
        return schedualServiceHi;
    }
}
