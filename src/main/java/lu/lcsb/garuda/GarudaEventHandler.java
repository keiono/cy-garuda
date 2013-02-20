package lu.lcsb.garuda;

import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;

public interface GarudaEventHandler {
	
	public String getCompatibleEventType();
	
	public void handleEvent(final GarudaBackendPropertyChangeEvent event);

}
