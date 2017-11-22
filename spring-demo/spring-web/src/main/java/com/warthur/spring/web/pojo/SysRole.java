package com.warthur.spring.web.pojo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_sys_role")
@Data
public class SysRole {

	@Id
	@GeneratedValue
	@Column(name = "role_id")
	private long roleId;
	@Column(name = "role_name")
	private String roleName;
}
