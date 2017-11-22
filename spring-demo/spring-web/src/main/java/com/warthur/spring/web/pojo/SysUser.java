package com.warthur.spring.web.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "t_sys_user")
@Data
public class SysUser implements UserDetails {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private long userId;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "update_time",
			columnDefinition = "datetime not null default current_timestamp on update current_timestamp",
			insertable = false,
			updatable = false)
	private Calendar updateTime;
	@Column(name = "create_time",
			columnDefinition = "datetime not null default current_timestamp",
			insertable = false,
			updatable = false)
	private Calendar createTime;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private List<SysRole> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<>();
		List<SysRole> roles = this.getRoles();
		for (SysRole role : roles) {
			auths.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return auths;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
