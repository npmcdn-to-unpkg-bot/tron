package com.pwc.utilities.tron.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PATCH_DB")
public class PatchDb extends BaseEntity {

	
	@ManyToOne()
	@JoinColumn(name = "patch_id")
	private Patch patch;
	
	@Column(name = "seq_no")
	private BigDecimal seqNo;
	
	@Column(name = "description")
	private String description;
	
	
	@Column(name = "table_name")
	private String tableName;
	
	@Column(name = "criteria")
	private String filterCriteria;
	
	@Column(name = "field_exclusions")
	private String fieldExclusions;
	
	@Column(name = "insert_allow_sw")
	private String inserAllowedSw;
	
	@Column(name = "update_allow_sw")
	private String updateAllowedSw;
	
	@Column(name = "delete_allow_sw")
	private String delete_allow_sw;
	
	@Column(name = "frest_install_sw")
	private String freshInstallSw;

	public Patch getPatch() {
		return patch;
	}

	public void setPatch(Patch patch) {
		this.patch = patch;
	}

	public BigDecimal getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(BigDecimal seqNo) {
		this.seqNo = seqNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFilterCriteria() {
		return filterCriteria;
	}

	public void setFilterCriteria(String filterCriteria) {
		this.filterCriteria = filterCriteria;
	}

	public String getFieldExclusions() {
		return fieldExclusions;
	}

	public void setFieldExclusions(String fieldExclusions) {
		this.fieldExclusions = fieldExclusions;
	}

	public String getInserAllowedSw() {
		return inserAllowedSw;
	}

	public void setInserAllowedSw(String inserAllowedSw) {
		this.inserAllowedSw = inserAllowedSw;
	}

	public String getUpdateAllowedSw() {
		return updateAllowedSw;
	}

	public void setUpdateAllowedSw(String updateAllowedSw) {
		this.updateAllowedSw = updateAllowedSw;
	}

	public String getDelete_allow_sw() {
		return delete_allow_sw;
	}

	public void setDelete_allow_sw(String delete_allow_sw) {
		this.delete_allow_sw = delete_allow_sw;
	}

	public String getFreshInstallSw() {
		return freshInstallSw;
	}

	public void setFreshInstallSw(String freshInstallSw) {
		this.freshInstallSw = freshInstallSw;
	}
	
}
