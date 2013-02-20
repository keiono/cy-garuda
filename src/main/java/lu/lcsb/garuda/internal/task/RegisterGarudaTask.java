package lu.lcsb.garuda.internal.task;

import java.io.File;

import jp.sbi.garuda.client.backend.BackendNotInitializedException;
import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.platform.commons.exception.NetworkException;
import jp.sbi.garuda.platform.commons.net.GarudaConnectionNotInitializedException;
import lu.lcsb.garuda.internal.GarudaChangeListener;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.ProvidesTitle;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;

public class RegisterGarudaTask extends AbstractTask {
	
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
		
		try {
			backend.registerGadgetToGaruda("open " + file.toString());
		} catch (NetworkException e1) {
			e1.printStackTrace();
		} catch (BackendNotInitializedException e1) {
			e1.printStackTrace();
		} catch (GarudaConnectionNotInitializedException e1) {
			e1.printStackTrace();
		}
	}

}
