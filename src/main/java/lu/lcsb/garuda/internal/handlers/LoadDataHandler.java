package lu.lcsb.garuda.internal.handlers;

import java.io.File;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import jp.sbi.garuda.platform.commons.Gadget;
import lu.lcsb.garuda.GarudaEventHandler;

import org.cytoscape.task.read.LoadNetworkFileTaskFactory;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadDataHandler implements GarudaEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(LoadDataHandler.class);
	
	private final LoadNetworkFileTaskFactory loadNetworkTF;
	private final DialogTaskManager taskManager;

	/**
	 * Handle the arrival of incoming data from another gadget through the Core
	 * 
	 * @param loadNetworkTF
	 * @param taskManager
	 */
	public LoadDataHandler(final LoadNetworkFileTaskFactory loadNetworkTF, final DialogTaskManager taskManager) {
		this.loadNetworkTF = loadNetworkTF;
		this.taskManager = taskManager;
	}

	@Override
	public void handleEvent(GarudaBackendPropertyChangeEvent event) {
		logger.info("Got Event from Garuda: " + event.getFirstProperty().toString());
		
		Gadget theOriginGadget = (Gadget) event.getFirstProperty();
		String theFilePath = (String) event.getSecondProperty();

		// TODO: handle multiple file types - SBML, genelist, and maybe CSV as table.
		File sbml = new File(theFilePath);

		final TaskIterator ti = loadNetworkTF.createTaskIterator(sbml);
		taskManager.execute(ti);
		
		logger.info("End of Load_Data_Handler");
	}

	@Override
	public String getCompatibleEventType() {
		return GarudaClientBackend.LOAD_DATA_PROPERTY_CHANGE_ID;
	}

}
