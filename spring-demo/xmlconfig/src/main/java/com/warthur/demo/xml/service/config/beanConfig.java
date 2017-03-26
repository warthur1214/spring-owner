package com.warthur.demo.xml.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by warthur on 17/3/26.
 */
@Configuration
@ImportResource(locations = {"classpath:application-bean.xml"})
public class beanConfig {

}
