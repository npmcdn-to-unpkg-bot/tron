package com.pwc.utilities.tron.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pwc.utilities.tron.model.entity.Patch;
import com.pwc.utilities.tron.model.entity.PatchDb;
import com.pwc.utilities.tron.services.AdminService;

@RestController
@RequestMapping("/db-objects")
public class DBObjectController {

	Logger logger = Logger.getLogger(DBObjectController.class);
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String generateBluePrint(@RequestBody Map<String, List<Map<String, List<Map<String, String>>>>> payload, HttpServletRequest request) {

		Path pathToOracle = Paths.get("Oracle").toAbsolutePath();
		Path storagePath  = Paths.get("storage").toAbsolutePath();
		
		Patch patch = (Patch) request.getSession().getAttribute("patch");
		
		

		try {
			
			// Create Package folder if not existing
			File file = new File(storagePath+File.separator+patch.getName());
			if (!file.exists()) {
				if (file.mkdir()) {
					logger.info(">>> Package Created under "+file);
					storagePath = file.toPath();
				}
				else {
					logger.error(">> Couldnot create folder "+file);
				}
			}
			
			
			File inpFile = new File(storagePath+File.separator+"input.inp");
			File inpListFile = new File(storagePath+File.separator+"input-list.inp");
			File logFile = new File(storagePath+File.separator+"output.log");
			
			PrintWriter inpFileWriter = new PrintWriter(inpFile);
			PrintWriter inpListFileWriter = new PrintWriter(inpListFile);
			PrintWriter logFileWriter = new PrintWriter(logFile);
			
			Iterator<Map<String, List<Map<String, String>>>> payloadListItr = payload.get("data").iterator();
			String objName = "";
			while(payloadListItr.hasNext())
			{
				Map<String, List<Map<String, String>>> payLoadMap = payloadListItr.next();
				List<Map<String, String>> tableRows = payLoadMap.get("objectType");
				Iterator<Map<String, String>> tableRowItr = tableRows.iterator();
				BigDecimal seqNo = BigDecimal.TEN;
				while(tableRowItr.hasNext()) {
					
					Map<String, String> tableDetails = tableRowItr.next();
					
					if(!tableDetails.get("objName").equalsIgnoreCase(objName))
					{
						objName = tableDetails.get("objName");
						inpFileWriter.println("########################################################################");
						inpListFileWriter.println("########################################################################");
						
						inpFileWriter.println("# "+objName);
						inpListFileWriter.println("# "+objName);
						
						inpFileWriter.println("########################################################################");
						inpListFileWriter.println("########################################################################");
					}
					inpFileWriter.println(tableDetails.get("table")+";"+tableDetails.get("condition")+";"+tableDetails.get("exclution"));
					inpListFileWriter.println(tableDetails.get("table")+";"+tableDetails.get("condition")+";"+tableDetails.get("inSw")+";"+tableDetails.get("upSw")+";"+tableDetails.get("dlSw")+";"+tableDetails.get("frSw")+";");
					PatchDb patchDb = new PatchDb();
					patchDb.setPatch(patch);
					patchDb.setSeqNo(seqNo);
					patchDb.setTableName(tableDetails.get("table"));
					patchDb.setFilterCriteria(tableDetails.get("condition"));
					patchDb.setFieldExclusions(tableDetails.get("exclution"));
					patchDb.setInserAllowedSw(tableDetails.get("inSw"));
					patchDb.setUpdateAllowedSw(tableDetails.get("upSw"));
					patchDb.setDelete_allow_sw(tableDetails.get("dlSw"));
					patchDb.setFreshInstallSw(tableDetails.get("frSw"));
					adminService.addPatchDb(patchDb);	
					seqNo = seqNo.add(BigDecimal.TEN);
					
				}
				inpFileWriter.println("");
				//inpFileWriter.println("########################################################################");
				//inpListFileWriter.println("########################################################################");
			}
			
			inpFileWriter.close();
			inpListFileWriter.close();
			
			
			ProcessBuilder pb = new ProcessBuilder(pathToOracle+File.separator+"CM-extract.bat",storagePath+File.separator+"input.inp",storagePath+File.separator+"blueprint");
			pb.directory(new File(pathToOracle.toString()));
			//pb.redirectOutput(new File(pathToOracle+File.separator+"out.log"));
			pb.redirectError(new File(storagePath+File.separator+"error.log"));
			Process process = pb.start();
			
			final BufferedReader wr = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        final BufferedWriter writer = new BufferedWriter(
	                new OutputStreamWriter(process.getOutputStream()));
	        String line = "";
	        while ((line = wr.readLine()) != null) {
	        	logger.info(line);
	        	logFileWriter.println(line);
	        }
	        logFileWriter.close();
			process.waitFor();
			
		} 
		catch (IOException e) 
		{		
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		finally
		{
			
		}
		
		return null;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, List<HashMap<String, String>>> retreive() {
		
		BufferedReader bufferedReader = null;
		logger.info(Paths.get("storage").toAbsolutePath());
		Map<String, List<HashMap<String, String>>> dbStore =  new HashMap<String, List<HashMap<String,String>>>();
		try 
		{
			bufferedReader = new BufferedReader(new FileReader(Paths.get("storage").toAbsolutePath()+File.separator+"systables.lst"));
			StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine();
            String key = null;
            List<HashMap<String, String>> storeVals = null;
            while (line != null) {
                if (line.startsWith("#$")) {
                    line = line.replace('#', ' ');
                    line = line.replace('$', ' ');

                    if (key != null) {
                        dbStore.put(key, storeVals);
                    }
                    key = line.trim();
                    //storeVals = new ArrayList<String>();
                    storeVals = new ArrayList<HashMap<String,String>>();
                    //storeVals.add(key);
                } else if ((!line.startsWith("##") && key != null)) {
                    line = line.replace('#', ' ');
                    line = line.replace('$', ' ');
                    String value = line.trim();
                    //storeVals.add(value + ";");
                    String values[] = value.split(";");
                    HashMap<String, String> myMap = new HashMap<String, String>();
                    logger.info(values[0]);
                    myMap.put("table", values[0]);
                    myMap.put("condition", values[1]);
                    myMap.put("exclution", values[2]);
                    myMap.put("inSw", "T");
                    myMap.put("upSw", "T");
                    myMap.put("dlSw", "F");
                    myMap.put("frSw", "T");
                    myMap.put("objName", key);
                    storeVals.add(myMap);
                    
                    
                    //System.out.println("\""+key+"\",\""+value+"\"");
                }
                line = bufferedReader.readLine();
            }
            logger.info(storeVals);
            logger.info("--------------------");
            logger.info(dbStore);
            
		} 
		catch (FileNotFoundException e) 
		{
			logger.error("systabled.lst File not found in the path "+Paths.get("storage").toAbsolutePath());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return dbStore;
		
	}
	
}
