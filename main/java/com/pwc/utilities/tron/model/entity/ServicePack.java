package com.pwc.utilities.tron.model.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICE_PACK")
public class ServicePack extends BaseEntity {

	
	@ManyToOne
	private Patch patch;
	
	private String createdBy;
	private Date createdDttm;
	private Date updatedDttm;
	private String status;
	
	@OneToMany(mappedBy = "servicePack", cascade = CascadeType.ALL)
	private Set<InstallRecord> installRecords;


}
