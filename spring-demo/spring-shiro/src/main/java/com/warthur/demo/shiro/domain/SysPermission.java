package com.warthur.demo.shiro.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/3/27.
 */
@Entity(name = "t_sys_permission")
public class SysPermission {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "permission_id")
	private long permssionId;
	@Column(length = 20)
	private String name;

	@Column(columnDefinition = "enum('menu', 'button')", name = "resource_type", length = 10)
	private String resourceType;
	@Column(length = 128)
	private String url;
	@Column(length = 20)
	private String permission;

	@Column(name = "parent_id")
	private Long parentId;
	@Column(name = "parent_ids", length = 128)
	private String parentIds;
	private Boolean available = Boolean.FALSE;

	@Column(name = "create_time",
			updatable = false,
			columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Column(name = "update_time",
			insertable = false,
			columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	@ManyToMany
	@JoinTable(name = "t_role_permission",
			joinColumns = {@JoinColumn(name = "permissionId")},
			inverseJoinColumns = {@JoinColumn(name = "roleId")})
	private List<RoleInfo> roles;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public long getPermssionId() {
		return permssionId;
	}

	public void setPermssionId(long permssionId) {
		this.permssionId = permssionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<RoleInfo> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleInfo> roles) {
		this.roles = roles;
	}
}
