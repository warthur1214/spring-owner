package com.warthur.demo.shiro.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/3/27.
 */
@Entity(name = "t_role_info")
public class RoleInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "role_id")
	private long roleId;
	@Column(length = 20)
	private String role;
	@Column(length = 128)
	private String description;
	private Boolean available = Boolean.FALSE;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_role_permission",
			joinColumns = {@JoinColumn(name = "roleId")},
			inverseJoinColumns = {@JoinColumn(name = "permissionId")})
	private List<SysPermission> permissions;

	@ManyToMany
	@JoinTable(name = "t_user_role",
			joinColumns = {@JoinColumn(name = "roleId")},
			inverseJoinColumns = {@JoinColumn(name = "userId")})
	private List<UserInfo> userInfos;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<SysPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<SysPermission> permissions) {
		this.permissions = permissions;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}
}
