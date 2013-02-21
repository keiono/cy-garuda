package lu.lcsb.garuda.internal.handlers;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import lu.lcsb.garuda.GarudaEventHandler;

import org.cytoscape.task.read.LoadNetworkFileTaskFactory;
import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionEstablishedHandler implements GarudaEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(ConnectionEstablishedHandler.class);
	
	private final LoadNetworkFileTaskFactory loadNetworkTF;
	private final DialogTaskManager taskManager;

	/**
	 * Handle the notification for the connection of the backend to the Core
	 * @param loadNetworkTF
	 * @param taskManager
	 */
	public ConnectionEstablishedHandler(final LoadNetworkFileTaskFactory loadNetworkTF, final DialogTaskManager taskManager) {
		this.loadNetworkTF = loadNetworkTF;
		this.taskManager = taskManager;
	}

	@Override
	public void handleEvent(GarudaBackendPropertyChangeEvent event) {
		logger.info("Got Event from Garuda: " + event.getFirstProperty().toString());
			
		logger.info("End of Connection_Established_Handler");
	}

	@Override
	public String getCompatibleEventType() {
		return GarudaClientBackend.GADGET_CONNECTION_ESTABLISHED_ID;
	}

}
