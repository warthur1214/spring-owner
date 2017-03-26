package com.warthur.demo.redis.repository;

import com.warthur.demo.redis.domain.DemoInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by warthur on 17/3/26.
 */
public interface DemoInfoRepository extends CrudRepository<DemoInfo, Long> {

}
