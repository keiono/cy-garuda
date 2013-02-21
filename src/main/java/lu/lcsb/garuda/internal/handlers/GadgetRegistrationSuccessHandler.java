package lu.lcsb.garuda.internal.handlers;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import lu.lcsb.garuda.GarudaEventHandler;

import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GadgetRegistrationSuccessHandler implements GarudaEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(GadgetRegistrationSuccessHandler.class);
	
	private final DialogTaskManager taskManager;
	
	/**
	 *  Handle the case that the registration process was successful
	 * 
	 * @param taskManager
	 */
	public GadgetRegistrationSuccessHandler(final DialogTaskManager taskManager) {
		this.taskManager = taskManager;
	}

	@Override
	public void handleEvent(GarudaBackendPropertyChangeEvent event) {
		logger.info("GadgetRegistrationSuccessHandler invoked through Garuda Event: " + event.getFirstProperty().toString());

		// Code to handle successful registration of gadget in Garuda.
		logger.info("Garuda Gadget Registration was successful.");
		
		logger.info("End of Gadget_Registration_Success_Handler");
	}

	@Override
	public String getCompatibleEventType() {
		return GarudaClientBackend.GADGET_REGISTRERED_ID;
	}
}
