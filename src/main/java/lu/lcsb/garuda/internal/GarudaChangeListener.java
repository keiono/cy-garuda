package lu.lcsb.garuda.internal;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import lu.lcsb.garuda.GarudaEventHandler;

import org.cytoscape.task.read.LoadNetworkFileTaskFactory;
import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GarudaChangeListener implements PropertyChangeListener {

	private static final Logger logger = LoggerFactory.getLogger(GarudaChangeListener.class);

	private final GarudaClientBackend backend;

	private final LoadNetworkFileTaskFactory loadNetworkTF;
	private final DialogTaskManager taskManager;

	private final Map<String, GarudaEventHandler> handlers;

	public GarudaChangeListener(final GarudaClientBackend backend, final LoadNetworkFileTaskFactory loadNetworkTF,
			final DialogTaskManager taskManager) {
		this.backend = backend;
		this.loadNetworkTF = loadNetworkTF;
		this.taskManager = taskManager;

		handlers = new HashMap<String, GarudaEventHandler>();
		
	}

	public void registerHandler(final GarudaEventHandler handler, Map props) {
		if (handler != null) {
			handlers.put(handler.getCompatibleEventType(), handler);
		}
	}

	public void deregisterHandler(final GarudaEventHandler handler, Map props) {

	}

	private GarudaEventHandler getHandler(final String propName) {
		return handlers.get(propName);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt instanceof GarudaBackendPropertyChangeEvent) {
			final GarudaBackendPropertyChangeEvent garudaPropertyEvt = (GarudaBackendPropertyChangeEvent) evt;

			logger.info("Received Property Change Call. Invoking corresponding handler...");
			
			final GarudaEventHandler handler = getHandler(garudaPropertyEvt.getPropertyName());
			if(handler == null) {
				logger.warn("Could not find handler.");
				return;
			} else {
				handler.handleEvent(garudaPropertyEvt);
			}
		}
	}
}
