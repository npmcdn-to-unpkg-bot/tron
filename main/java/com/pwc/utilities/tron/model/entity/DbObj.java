package com.pwc.utilities.tron.model.entity;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DB_OBJ")
public class DbObj extends BaseEntity {

	private String defFilterCriteria;
	private String defFilterExclusions;
	private String defInsertAllwIndc;
	private String defUpdateAllwIndc;
	private String defDeleteAllwIndc;
	private String defFrshInstlAllwIndc;
	
	//@OneToMany(mappedBy = "dbObj", cascade = CascadeType.ALL)
	//protected Set<PatchDb> patchDbs;
}
