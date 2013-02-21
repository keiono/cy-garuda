package lu.lcsb.garuda.internal.handlers;

import java.util.List;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import jp.sbi.garuda.platform.commons.Gadget;
import lu.lcsb.garuda.GarudaEventHandler;

import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompatibleGadgetsHandler implements GarudaEventHandler {

	private static final Logger logger = LoggerFactory.getLogger(CompatibleGadgetsHandler.class);
	
	private final DialogTaskManager taskManager;
	private final GarudaClientBackend garudaBackend;

	/**
	 * Handle the incoming list of compatible gadgets for a previously
	 * sent request (see next section for more details)
	 * @param taskManager
	 * @param garudaBackend Garuda Backend instance
	 */
	public CompatibleGadgetsHandler(final DialogTaskManager taskManager, final GarudaClientBackend garudaBackend) {
		this.taskManager = taskManager;
		this.garudaBackend = garudaBackend;
	}

	@Override
	public void handleEvent(GarudaBackendPropertyChangeEvent event) {
		logger.info("Got Event from Garuda: " + event.getFirstProperty().toString());
		
		Gadget loadableGadget = (Gadget) event.getFirstProperty();
		String launchPathofGadget = (String) event.getSecondProperty();
		List<Gadget> listOfCompatibleGadgets = garudaBackend.getCompatibleGadgetList();
		
		// rest of the code to handle the list of compatible gadgets
		logger.info("End of Compatible_Gadgets_Handler");
	}

	@Override
	public String getCompatibleEventType() {
		return GarudaClientBackend.GOT_GADGETS_PROPERTY_CHANGE_ID;
	}
}
