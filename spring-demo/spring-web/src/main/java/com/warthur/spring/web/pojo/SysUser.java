package com.warthur.spring.web.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_sys_user")
@Data
public class SysUser {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private long userId;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "password")
	private String password;
}
