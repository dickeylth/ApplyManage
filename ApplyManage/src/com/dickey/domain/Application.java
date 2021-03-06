package com.dickey.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Application implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="applicationType_id")
	private ApplicationType applicationType;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;
	
	@Column
	private String reason;
	
	//流程状态
	@OneToOne(cascade = CascadeType.ALL)
	private BizWorkflow bizWorkflow;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ApplicationType getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(ApplicationType applicationType) {
		this.applicationType = applicationType;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public BizWorkflow getBizWorkflow() {
		return bizWorkflow;
	}

	public void setBizWorkflow(BizWorkflow bizWorkflow) {
		this.bizWorkflow = bizWorkflow;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", user=" + user + ", start=" + start
				+ ", end=" + end + ", reason=" + reason + "]";
	}
	
}
