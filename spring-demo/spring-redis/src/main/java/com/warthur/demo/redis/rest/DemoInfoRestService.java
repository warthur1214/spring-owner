package com.warthur.demo.redis.rest;

import com.warthur.demo.redis.domain.DemoInfo;
import com.warthur.demo.redis.service.DemoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by warthur on 17/3/26.
 */
@RestController
public class DemoInfoRestService {

    @Autowired
    private DemoInfoService demoInfoService;

    @GetMapping("test")
    public String test() {
        DemoInfo loaded = demoInfoService.findById(1);
        System.out.println("loaded=" + loaded);
        DemoInfo cached = demoInfoService.findById(1);
        System.out.println("cached=" + cached);
        loaded = demoInfoService.findById(2);
        System.out.println("loaded2=" + loaded);
        return "ok";
    }

    @GetMapping("/delete")
    public String delete(long id) {
        demoInfoService.deleteFromCache(id);
        return "ok";
    }

    @GetMapping("/test1")
    public String test1() {
        demoInfoService.test();
        System.out.println("DemoInfoController.test1()");
        return "ok";
    }
}
