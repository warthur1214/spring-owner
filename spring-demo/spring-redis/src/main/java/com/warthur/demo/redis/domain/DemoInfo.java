package com.warthur.demo.redis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by warthur on 17/3/26.
 */
@Entity(name = "tp_user")
public class DemoInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue @Column(name = "use_id")
    private long userId;
    private String tel;
    private String password;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
