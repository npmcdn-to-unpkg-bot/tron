package com.pwc.utilities.tron.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PATCH_APP")
public class PatchApp extends BaseEntity {

	private AppObjTypeFlg appObjTypeFlg;
	
	@ManyToOne()
	@JoinColumn(name = "patch_id")
	private Patch patch;
	

}
