package lu.lcsb.garuda.internal.task;

import java.io.File;

import jp.sbi.garuda.client.backend.BackendNotInitializedException;
import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.platform.commons.exception.NetworkException;
import jp.sbi.garuda.platform.commons.net.GarudaConnectionNotInitializedException;
import lu.lcsb.garuda.internal.CyActivator;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterGarudaTask extends AbstractTask {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterGarudaTask.class);
	
	@ProvidesTitle
	public String getTitle() {
		return "Register Cytoscape as Garuda Gadget";
	}
	
	@Tunable(description = "Cytoscape executable file (.bat or .sh)", params = "fileCategory=UNSPECIFIED;input=true")
	public File file;
	
	private final GarudaClientBackend backend;
	
	public RegisterGarudaTask(final GarudaClientBackend backendGaurda) {
		this.backend = backendGaurda;
	}

	@Override
	public void run(TaskMonitor taskMonitor) throws Exception {
		System.out.println("Cytoscape executable = " + file.toString());
		
		String os_name = System.getProperty("os.name").toLowerCase();;
        String launchCommand = "";
		
		if (os_name.startsWith("mac")) {
			launchCommand = "open \"/" + file.toString() + "\"";
		} else if (os_name.startsWith("windows")) {
			launchCommand = "cmd /c  \"" + file.toString() + "\"";
		} //TODO: Handle Ubuntu / Linux versions
		
		logger.info("Garuda Launch Command" + launchCommand);
		
		try {
			backend.registerGadgetToGaruda(launchCommand);
		} catch (NetworkException e1) {
			e1.printStackTrace();
		} catch (BackendNotInitializedException e1) {
			logger.error("Garuda Backend Not initialzed", e1);
		} catch (GarudaConnectionNotInitializedException e1) {
			e1.printStackTrace();
		}
	}

}
