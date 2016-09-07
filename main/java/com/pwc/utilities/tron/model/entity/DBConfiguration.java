package com.pwc.utilities.tron.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "configurations")
public class DBConfiguration extends BaseEntity {

    @Column(name = "connection_name")
    @NotEmpty
    protected String connectionName;
    
    @Column(name = "db_host")
    @NotEmpty
    protected String dbHost;
    
    @Column(name = "db_port")
    @NotEmpty
    protected String dbPort;
    
    @Column(name = "db_sid")
    @NotEmpty
    protected String dbSid;
    
    @Column(name = "db_username")
    @NotEmpty
    protected String dbUsername;
    
    @Column(name = "db_password")
    @NotEmpty
    protected String dbPassword;
    
    @Column(name = "spl_ebase")
    @NotEmpty
    protected String splEbase;

	public String getConnectionName() {
		return connectionName;
	}

	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbSid() {
		return dbSid;
	}

	public void setDbSid(String dbSid) {
		this.dbSid = dbSid;
	}

	public String getDbUsername() {
		return dbUsername;
	}

	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getSplEbase() {
		return splEbase;
	}

	public void setSplEbase(String splEbase) {
		this.splEbase = splEbase;
	}
	
    
}
