package lu.lcsb.garuda.internal.handlers.errors;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import lu.lcsb.garuda.GarudaEventHandler;

import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GarudaCoreConnTerminatedHandler implements GarudaEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(GarudaCoreConnTerminatedHandler.class);
	
	private final DialogTaskManager taskManager;
	private final GarudaClientBackend garudaBackend;
	
	/**
	 * Handle the notification for the disconnection from the Core
	 * 
	 * @param taskManager
	 * @param garudaBackend Garuda Backend instance
	 */
	public GarudaCoreConnTerminatedHandler(final DialogTaskManager taskManager, final GarudaClientBackend garudaBackend) {
		this.taskManager = taskManager;
		this.garudaBackend = garudaBackend;
	}

	@Override
	public void handleEvent(GarudaBackendPropertyChangeEvent event) {
		logger.info("GarudaCoreConnTerminatedHandler invoked through Garuda Event: " + event.getFirstProperty().toString());

		// Code to handle the case that the Garuda Core disconnects / crashes.
		logger.error("Connection to Garuda has been terminated.");
		
		logger.info("End of Garuda_Core_Conn_Terminated_Handler");
	}

	@Override
	public String getCompatibleEventType() {
		return GarudaClientBackend.CONNECTION_TERMINATED_ID;
	}
}
