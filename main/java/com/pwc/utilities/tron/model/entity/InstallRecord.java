package com.pwc.utilities.tron.model.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INSTALL_RECORD")
public class InstallRecord extends BaseEntity  {

	@ManyToOne
    @JoinColumn(name = "env_id")
	private Environment environment;
	
	@ManyToOne
    @JoinColumn(name = "sp_id")
	private ServicePack servicePack;
	
	@ManyToOne
    @JoinColumn(name = "patch_id")
	private Patch patch;
	
	@Column(name = "apply_user")
	private String applyUser;
	
	@Column(name = "apply_dttm")
	private Date applyDttm;

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public ServicePack getServicePack() {
		return servicePack;
	}

	public void setServicePack(ServicePack servicePack) {
		this.servicePack = servicePack;
	}

	public Patch getPatch() {
		return patch;
	}

	public void setPatch(Patch patch) {
		this.patch = patch;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public Date getApplyDttm() {
		return applyDttm;
	}

	public void setApplyDttm(Date applyDttm) {
		this.applyDttm = applyDttm;
	}

	
}
