package lu.lcsb.garuda.internal.handlers;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import lu.lcsb.garuda.GarudaEventHandler;

import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendDataSuccessHandler implements GarudaEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(SendDataSuccessHandler.class);
	
	private final DialogTaskManager taskManager;
	private final GarudaClientBackend garudaBackend;
	
	/**
	 * Handle the response that deals with a sent data request successfully
	 * 
	 * @param taskManager
	 * @param garudaBackend
	 */
	public SendDataSuccessHandler(final DialogTaskManager taskManager, final GarudaClientBackend garudaBackend) {
		this.taskManager = taskManager;
		this.garudaBackend = garudaBackend;
	}

	@Override
	public void handleEvent(GarudaBackendPropertyChangeEvent event) {
		logger.info("SendDataSuccessHandler invoked through Garuda Event: " + event.getFirstProperty().toString());

		// Code to handle when a send request to Garuda results in success.
		
		logger.info("End of Send_Data_Success_Handler");
	}

	@Override
	public String getCompatibleEventType() {
		return GarudaClientBackend.SENT_DATA_RECEIVED_RESPONSE;
	}
}
