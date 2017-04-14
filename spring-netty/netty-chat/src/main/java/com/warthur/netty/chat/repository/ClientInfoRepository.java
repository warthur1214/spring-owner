package com.warthur.netty.chat.repository;

import com.warthur.netty.chat.bean.ClientInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by admin on 2017/4/14.
 */
public interface ClientInfoRepository extends CrudRepository<ClientInfo, String> {
	ClientInfo findClientByclientid(String clientId);
}
