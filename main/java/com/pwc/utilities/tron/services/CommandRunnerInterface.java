package com.pwc.utilities.tron.services;

public interface CommandRunnerInterface {
	
	public void createApplicationPatch();
	
	public void createDatabasePatch();
	
	public void applyApplicationPatch();
	
	public void applyDatabasePatch();
	
}
