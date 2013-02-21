package lu.lcsb.garuda;

import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;

/**
 * Public API for Garuda event handler.
 * 
 *
 */
public interface GarudaEventHandler {
	
	/**
	 * Returns name of event can be handled by this handler.
	 * 
	 * @return pre-defined event name by Garuda Alliance.  See (link to their doc)
	 */
	public String getCompatibleEventType();
	
	/**
	 * Actual event handler.  Based on the objects in the event, handler will 
	 *  - Load network
	 *  - Export table
	 *  - etc.
	 *  
	 * @param event event object from Garuda core.
	 */
	public void handleEvent(final GarudaBackendPropertyChangeEvent event);

}
