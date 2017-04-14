package com.warthur.netty.chat.bean;

import com.sun.istack.internal.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by admin on 2017/4/14.
 */
@Entity
@Table(name = "t_clientinfo")
public class ClientInfo {
	@Id
	@NotNull
	private String clientid;
	private Short connected;
	private Long mostsignbits;
	private Long leastsignbits;
	private Date lastconnecteddate;

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public Short getConnected() {
		return connected;
	}

	public void setConnected(Short connected) {
		this.connected = connected;
	}

	public Long getMostsignbits() {
		return mostsignbits;
	}

	public void setMostsignbits(Long mostsignbits) {
		this.mostsignbits = mostsignbits;
	}

	public Long getLeastsignbits() {
		return leastsignbits;
	}

	public void setLeastsignbits(Long leastsignbits) {
		this.leastsignbits = leastsignbits;
	}

	public Date getLastconnecteddate() {
		return lastconnecteddate;
	}

	public void setLastconnecteddate(Date lastconnecteddate) {
		this.lastconnecteddate = lastconnecteddate;
	}
}
