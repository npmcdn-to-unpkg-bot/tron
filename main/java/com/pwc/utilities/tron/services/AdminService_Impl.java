package com.pwc.utilities.tron.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pwc.utilities.tron.model.entity.Dashboard;
import com.pwc.utilities.tron.model.entity.DbObj;
import com.pwc.utilities.tron.model.entity.Environment;
import com.pwc.utilities.tron.model.entity.InstallRecord;
import com.pwc.utilities.tron.model.entity.Patch;
import com.pwc.utilities.tron.model.entity.PatchApp;
import com.pwc.utilities.tron.model.entity.PatchDb;
import com.pwc.utilities.tron.model.entity.ServicePack;
import com.pwc.utilities.tron.model.repository.DBConfigurationRepository;
import com.pwc.utilities.tron.model.repository.DashboardRepository;
import com.pwc.utilities.tron.model.repository.DbObjRepository;
import com.pwc.utilities.tron.model.repository.EnvironmentRepository;
import com.pwc.utilities.tron.model.repository.InstallRecordRepository;
import com.pwc.utilities.tron.model.repository.PatchAppRepository;
import com.pwc.utilities.tron.model.repository.PatchDbRepository;
import com.pwc.utilities.tron.model.repository.PatchRepository;
import com.pwc.utilities.tron.model.repository.ServicePackRepository;

@Service
class AdminService_Impl implements AdminService {

	//@Autowired
	private DBConfigurationRepository dbConfigRepo;
	
	//@Autowired
	private DbObjRepository dbObjRepo;
	
	@Autowired
	private EnvironmentRepository envrRepo;
	
	@Autowired
	private InstallRecordRepository instRecRepo;
	
	@Autowired
	private PatchDbRepository patchDbRepo;
	
	//@Autowired
	private PatchAppRepository patchAppRepo;
	
	@Autowired
	private PatchRepository patchRepo;
	
	//@Autowired
	private ServicePackRepository servicePackRepo;
	
	//@Autowired
	private DashboardRepository dashboardRepo;


	@Override
	public Iterable<Environment> getAllEnvironemnts() {
		// TODO Auto-generated method stub
		return envrRepo.findAll();
	}
	
	@Override
	public Environment getEnvironment(Integer envID) {
		// TODO Auto-generated method stub
		return envrRepo.findOne(envID);
	}

	@Override
	public Environment addEnvironment(Environment environment) {
		return envrRepo.save(environment);
	}
	@Override
	public void deleteEnvironment(Integer envId) {
		// TODO Auto-generated method stub
		envrRepo.delete(envId);
	}
	
	@Override
	public void deleteEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		envrRepo.delete(environment);
	}

	
	public Iterable<Patch> getPatches() {
		return patchRepo.findAll();
	}
	
	@Override
	public Patch getPatch(Integer patchId) {
		// TODO Auto-generated method stub
		return patchRepo.findOne(patchId);
	}

	@Override
	public Patch addPatch(Patch patch) {
		// TODO Auto-generated method stub
		return patchRepo.save(patch);
	}

	@Override
	public void deletePatch(Integer patchId) {
		// TODO Auto-generated method stub
		patchRepo.delete(patchId);
	}

	
	@Override
	public Iterable<DbObj> getAllDBObject() {
		// TODO Auto-generated method stub
		return dbObjRepo.findAll();
	}

	@Override
	public DbObj getDBObject(Integer dbObjId) {
		// TODO Auto-generated method stub
		return dbObjRepo.findOne(dbObjId);
	}

	@Override
	public DbObj addDBObject(DbObj dbObj) {
		// TODO Auto-generated method stub
		return dbObjRepo.save(dbObj);
	}

	@Override
	public void deleteDBBObject(Integer dbObjId) {
		// TODO Auto-generated method stub
		dbObjRepo.delete(dbObjId);
	}

	@Override
	public void deleteDBBObject(DbObj dbObject) {
		// TODO Auto-generated method stub
		dbObjRepo.delete(dbObject);
	}

	@Override
	public Iterable<PatchDb> getAllPatchDB() {
		// TODO Auto-generated method stub
		return patchDbRepo.findAll();
	}

	

	@Override
	public PatchDb getPatchDB(Integer patchId) {
		// TODO Auto-generated method stub
		return patchDbRepo.findOne(patchId);
	}

	@Override
	public PatchDb addPatchDb(PatchDb patchDb) {
		// TODO Auto-generated method stub
		return patchDbRepo.save(patchDb);
	}

	@Override
	public void deletePatchDB(Integer patchId) {
		// TODO Auto-generated method stub
		patchDbRepo.delete(patchId);
		
	}

	@Override
	public void deletePatchDB(PatchDb patchDB) {
		// TODO Auto-generated method stub
		patchDbRepo.delete(patchDB);
		
	}

	@Override
	public Iterable<PatchApp> getAllPatchApp() {
		// TODO Auto-generated method stub
		return patchAppRepo.findAll();
	}

	@Override
	public PatchApp getPatchApp(Integer patchId) {
		// TODO Auto-generated method stub
		return patchAppRepo.findOne(patchId);
	}

	@Override
	public PatchApp addPatchApp(PatchApp patchApp) {
		// TODO Auto-generated method stub
		return patchAppRepo.save(patchApp);
	}

	@Override
	public void deletePatchApp(Integer patchId) {
		// TODO Auto-generated method stub
		patchAppRepo.delete(patchId);
	}

	@Override
	public void deletePatchApp(PatchApp patchApp) {
		// TODO Auto-generated method stub
		patchAppRepo.delete(patchApp);
		
	}

	@Override
	public Iterable<ServicePack> getAllServicePackes() {
		// TODO Auto-generated method stub
		return servicePackRepo.findAll();
	}

	@Override
	public ServicePack getServicePack(Integer spId) {
		// TODO Auto-generated method stub
		return servicePackRepo.findOne(spId);
	}

	@Override
	public ServicePack addServicePack(ServicePack servicePack) {
		// TODO Auto-generated method stub
		return servicePackRepo.save(servicePack);
	}

	@Override
	public void deleteServicePack(Integer spId) {
		// TODO Auto-generated method stub
		servicePackRepo.delete(spId);
	}

	@Override
	public void deleteServicePack(ServicePack servicePack) {
		// TODO Auto-generated method stub
		servicePackRepo.delete(servicePack);
	}

	@Override
	public Iterable<InstallRecord> getAllInstallRecords() {
		// TODO Auto-generated method stub
		return instRecRepo.findAll();
	}

	@Override
	public InstallRecord getInstallRecord(Integer installId) {
		// TODO Auto-generated method stub
		return instRecRepo.findOne(installId);
	}

	@Override
	public InstallRecord addInstallRecord(InstallRecord installRecord) {
		// TODO Auto-generated method stub
		return instRecRepo.save(installRecord);
	}

	@Override
	public void deleteInstallRecord(Integer installId) {
		// TODO Auto-generated method stub
		instRecRepo.delete(installId);
	}

	@Override
	public void deleteInstallRecord(InstallRecord installRecord) {
		// TODO Auto-generated method stub
		instRecRepo.delete(installRecord);
	}


	@Override
	public Iterable<Dashboard> getDashboard() {
		return dashboardRepo.findAll();
	}

	
	

	@Override
	public void createNewEnvironment(String dbHost, int dbPort, String dbPswd, String dbUser, String dbSid,
			String envName, String envPath, String envVer, String project) {
		
	}

	@Override
	public Iterable<Object> getContentOfPatch() {
		return null;
	}



}
