package lu.lcsb.garuda.internal.handlers.errors;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import lu.lcsb.garuda.GarudaEventHandler;

import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionNotInitializedHandler implements GarudaEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(ConnectionNotInitializedHandler.class);
	
	private final DialogTaskManager taskManager;
	/**
	 * Handle the case that there is no proper initialization of the Backend
	 * 
	 * @param taskManager
	 */
	public ConnectionNotInitializedHandler(final DialogTaskManager taskManager) {
		this.taskManager = taskManager;
	}

	@Override
	public void handleEvent(GarudaBackendPropertyChangeEvent event) {
		logger.info("ConnectionNotInitializedHandler invoked through Garuda Event: " + event.getFirstProperty().toString());

		// Code to handle error when connection could not be initialized.
		logger.error("Connection to Garuda could not be initialized.");
		
		logger.info("End of Connection_Not_Initialized_Handler");
	}

	@Override
	public String getCompatibleEventType() {
		return GarudaClientBackend.CONNECTION_NOT_INITIALIZED_ID;
	}
}
