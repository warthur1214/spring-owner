package com.warthur.demo.shiro.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/3/27.
 */
@Entity(name = "t_user_info")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "user_id")
	private long userId;

	@Column(unique = true, name = "user_name", length = 20)
	private String userName;

	@Column(name = "nick_name", length = 50)
	private String nickName;
	@Column(length = 50)
	private String password;
	@Column(length = 20)
	private String salt;
	private byte state;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_user_role",
			joinColumns = { @JoinColumn(name = "userId") },
			inverseJoinColumns ={@JoinColumn(name = "roleId") })
	private List<RoleInfo> roleList;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public List<RoleInfo> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleInfo> roleList) {
		this.roleList = roleList;
	}
}
