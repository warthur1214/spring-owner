package com.warthur.demo.redis.service;

import com.warthur.demo.redis.domain.DemoInfo;
import org.springframework.stereotype.Service;

/**
 * Created by warthur on 17/3/26.
 */
public interface DemoInfoService {

    DemoInfo findById(long id);

    void deleteFromCache(long id);

    void test();
}
