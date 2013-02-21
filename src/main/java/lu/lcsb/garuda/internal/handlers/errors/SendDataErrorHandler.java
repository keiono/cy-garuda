package lu.lcsb.garuda.internal.handlers.errors;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import lu.lcsb.garuda.GarudaEventHandler;

import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendDataErrorHandler implements GarudaEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(SendDataErrorHandler.class);
	
	private final DialogTaskManager taskManager;
	private final GarudaClientBackend garudaBackend;
	
	/**
	 * Handle the response that deals with a sent data request unsuccessfully
	 * 
	 * @param taskManager
	 */
	public SendDataErrorHandler(final DialogTaskManager taskManager, final GarudaClientBackend garudaBackend) {
		this.taskManager = taskManager;
		this.garudaBackend = garudaBackend;
	}

	@Override
	public void handleEvent(GarudaBackendPropertyChangeEvent event) {
		logger.info("SendDataErrorHandler invoked through Garuda Event: " + event.getFirstProperty().toString());

		// Code to handle when a send request to Garuda results in an error.
		logger.error("Send Data Request returned an error message");
		
		logger.info("End of Send_Data_Error_Handler");
	}

	@Override
	public String getCompatibleEventType() {
		return GarudaClientBackend.SENT_DATA_RECEIVED_RESPONSE_ERROR;
	}
}
