package com.warthur.spring.web.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

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
	@Column(name = "update_time",
			columnDefinition = "datetime not null default current_timestamp on update current_timestamp",
			insertable = false,
			updatable = false)
	private Calendar updateTime;
	@Column(name = "update_time",
			columnDefinition = "datetime not null default current_timestamp",
			insertable = false,
			updatable = false)
	private Calendar createTime;
}
