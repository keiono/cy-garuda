package lu.lcsb.garuda.internal.handlers;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import lu.lcsb.garuda.GarudaEventHandler;

import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionEstablishedHandler implements GarudaEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(ConnectionEstablishedHandler.class);
	
	private final DialogTaskManager taskManager;

	/**
	 * Handle the notification for the connection of the backend to the Core
	 * 
	 * @param taskManager
	 */
	public ConnectionEstablishedHandler(final DialogTaskManager taskManager) {
		this.taskManager = taskManager;
	}

	@Override
	public void handleEvent(GarudaBackendPropertyChangeEvent event) {
		logger.info("ConnectionEstablishedHandler invoked through Garuda Event: " + event.getFirstProperty().toString());
			
		logger.info("End of Connection_Established_Handler");
	}

	@Override
	public String getCompatibleEventType() {
		return GarudaClientBackend.GADGET_CONNECTION_ESTABLISHED_ID;
	}

}
