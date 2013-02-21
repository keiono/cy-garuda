package lu.lcsb.garuda.internal.handlers.errors;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import lu.lcsb.garuda.GarudaEventHandler;

import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GarudaCoreErrorHandler implements GarudaEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(GarudaCoreErrorHandler.class);
	
	private final DialogTaskManager taskManager;
	private final GarudaClientBackend garudaBackend;
	
	/**
	 * Handle Error Messages that will be sent from the Core or the Backend
	 * 
	 * @param taskManager
	 * @param garudaBackend Garuda Backend instance
	 */
	public GarudaCoreErrorHandler(final DialogTaskManager taskManager, final GarudaClientBackend garudaBackend) {
		this.taskManager = taskManager;
		this.garudaBackend = garudaBackend;
	}

	@Override
	public void handleEvent(GarudaBackendPropertyChangeEvent event) {
		logger.info("GarudaCoreErrorHandler invoked through Garuda Event: " + event.getFirstProperty().toString());

		// Code to handle errors coming from the Garuda Core or the Garuda Backend
		logger.error("Received an error message from Garuda Core");
		
		logger.info("End of Garuda_Core_Error_Handler");
	}

	@Override
	public String getCompatibleEventType() {
		return GarudaClientBackend.GOT_ERRORS_PROPERTY_CHANGE_ID;
	}
}
