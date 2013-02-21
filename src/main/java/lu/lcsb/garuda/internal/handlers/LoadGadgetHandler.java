package lu.lcsb.garuda.internal.handlers;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import jp.sbi.garuda.platform.commons.Gadget;
import lu.lcsb.garuda.GarudaEventHandler;

import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadGadgetHandler implements GarudaEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(LoadGadgetHandler.class);
	
	private final DialogTaskManager taskManager;

	/**
	 * Handle the notification for the connection of the backend to the Core
	 * @param taskManager
	 */
	public LoadGadgetHandler(final DialogTaskManager taskManager) {
		this.taskManager = taskManager;
	}

	@Override
	public void handleEvent(GarudaBackendPropertyChangeEvent event) {
		logger.info("LoadGadgetHandler invoked through Garuda Event: " + event.getFirstProperty().toString());
		Gadget loadableGadget = (Gadget) event.getFirstProperty();
		String launchPathofGadget = (String) event.getSecondProperty();

		// rest of code to handle the loading of gadget	
		logger.info("End of Load_Gadget_Handler");
	}

	@Override
	public String getCompatibleEventType() {
		return GarudaClientBackend.LOAD_GADGET_PROPERTY_CHANGE_ID;
	}
}
