package lu.lcsb.garuda.internal.handlers.errors;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import lu.lcsb.garuda.GarudaEventHandler;

import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GadgetRegistrationErrorHandler implements GarudaEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(GadgetRegistrationErrorHandler.class);
	
	private final DialogTaskManager taskManager;
	private final GarudaClientBackend garudaBackend;
	
	/**
	 *  Handle the case that the registration process has some kind of error
	 * 
	 * @param taskManager
	 * @param garudaBackend Garuda Backend instance
	 */
	public GadgetRegistrationErrorHandler(final DialogTaskManager taskManager, final GarudaClientBackend garudaBackend) {
		this.taskManager = taskManager;
		this.garudaBackend = garudaBackend;
	}

	@Override
	public void handleEvent(GarudaBackendPropertyChangeEvent event) {
		logger.error("Got Event from Garuda: " + event.getFirstProperty().toString());
		logger.error("Garuda Gadget Registration Failed.");

		// Code to handle error when registering gadget to Garuda.
		
		logger.info("End of Gadget_Registration_Error_Handler");
	}

	@Override
	public String getCompatibleEventType() {
		return GarudaClientBackend.GADGET_REGISTRATION_ERROR_ID;
	}
}
