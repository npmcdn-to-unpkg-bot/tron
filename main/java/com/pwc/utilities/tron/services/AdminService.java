package com.pwc.utilities.tron.services;

import com.pwc.utilities.tron.model.entity.Dashboard;
import com.pwc.utilities.tron.model.entity.DbObj;
import com.pwc.utilities.tron.model.entity.Environment;
import com.pwc.utilities.tron.model.entity.InstallRecord;
import com.pwc.utilities.tron.model.entity.Patch;
import com.pwc.utilities.tron.model.entity.PatchApp;
import com.pwc.utilities.tron.model.entity.PatchDb;
import com.pwc.utilities.tron.model.entity.ServicePack;


public interface AdminService {
	
	Iterable<Environment> getAllEnvironemnts();
	Environment getEnvironment(Integer envID);
	Environment addEnvironment(Environment environment);
	void deleteEnvironment(Environment environment);
	void deleteEnvironment(Integer envId);
	
	Iterable<Patch> getPatches();
	Patch getPatch(Integer patchId);
	Patch addPatch(Patch patch);
	void deletePatch(Integer patchId);
	
	Iterable<DbObj> getAllDBObject();
	DbObj getDBObject(Integer dbObjId);
	DbObj addDBObject(DbObj dbObj);
	void deleteDBBObject(Integer dbObjId);
	void deleteDBBObject(DbObj dbObject);
	
	Iterable<PatchDb> getAllPatchDB(); 
	PatchDb getPatchDB(Integer patchId);
	PatchDb addPatchDb(PatchDb patchId);
	void deletePatchDB(Integer patchId);
	void deletePatchDB(PatchDb patchDB);
	
	Iterable<PatchApp> getAllPatchApp();
	PatchApp getPatchApp(Integer patchId);
	PatchApp addPatchApp(PatchApp patchId);
	void deletePatchApp(Integer patchId);
	void deletePatchApp(PatchApp patchDB);
	
	Iterable<ServicePack> getAllServicePackes();
	ServicePack getServicePack(Integer spId);
	ServicePack addServicePack(ServicePack servicePack);
	void deleteServicePack(Integer spId);
	void deleteServicePack(ServicePack servicePack);
	
	Iterable<InstallRecord> getAllInstallRecords();
	InstallRecord getInstallRecord(Integer installId);
	InstallRecord addInstallRecord(InstallRecord installRecord);
	void deleteInstallRecord(Integer installId);
	void deleteInstallRecord(InstallRecord installRecord);
	
	Iterable<Dashboard> getDashboard();
	
	Iterable<Object> getContentOfPatch();
	
	public void createNewEnvironment(String dbHost, int dbPort, String dbPswd, String dbUser, String dbSid,	String envName, String envPath, String envVer, String project);
	
	
	
}
