package com.warthur.spring.web.pojo;

import javax.persistence.*;

@Entity
@Table(name = "t_sys_role")

public class SysRole {

	@Id
	@GeneratedValue
	@Column(name = "role_id")
	private long roleId;
	@Column(name = "role_name")
	private String roleName;
}
