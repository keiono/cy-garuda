package lu.lcsb.garuda.internal;

import java.util.Properties;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.platform.commons.net.GarudaConnectionNotInitializedException;
import lu.lcsb.garuda.GarudaEventHandler;
import lu.lcsb.garuda.internal.handlers.ConnectionEstablishedHandler;
import lu.lcsb.garuda.internal.handlers.LoadDataHandler;
import lu.lcsb.garuda.internal.handlers.LoadGadgetHandler;
import lu.lcsb.garuda.internal.task.RegisterGarudaTaskFactory;

import org.cytoscape.application.CyVersion;
import org.cytoscape.property.CyProperty;
import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.task.read.LoadNetworkFileTaskFactory;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.swing.DialogTaskManager;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CyActivator extends AbstractCyActivator {
	
	private static final Logger logger = LoggerFactory.getLogger(CyActivator.class);

	private static final String GARUDA_REGISTERED = "garuda.registered";

	public CyActivator() {
		super();
	}

	@Override
	public void start(BundleContext bc) {

		// Service imports
		final CyVersion version = getService(bc, CyVersion.class);
		final DialogTaskManager taskManager = getService(bc, DialogTaskManager.class);
		final CyProperty<Properties> cytoscapePropertiesServiceRef = getService(bc, CyProperty.class,
				"(cyPropertyName=cytoscape3.props)");
		final LoadNetworkFileTaskFactory loadNetworkTF = getService(bc, LoadNetworkFileTaskFactory.class);

		// Garuda service objects
		final GarudaLauncher garudaLauncher = new GarudaLauncher(version);
		final GarudaClientBackend backendGaurda = garudaLauncher.getClientBackend();

		final GarudaChangeListener changeListener = new GarudaChangeListener(backendGaurda, loadNetworkTF, taskManager);
		backendGaurda.addGarudaChangeListener(changeListener);

		try {
			backendGaurda.initialize();
		} catch (GarudaConnectionNotInitializedException err) {
			logger.error("Could not initialize GarudaClientBackend.", err);
		}
		
		final Properties props = cytoscapePropertiesServiceRef.getProperties();
		final String isRegistered = props.getProperty(GARUDA_REGISTERED);
		if (isRegistered == null) {
			final TaskFactory registerGarudaTaskFactory = new RegisterGarudaTaskFactory(backendGaurda);
			taskManager.execute(registerGarudaTaskFactory.createTaskIterator());
			props.setProperty(GARUDA_REGISTERED, "true");
		}
		
		// Create handlers
		GarudaEventHandler loadDataHandler = new LoadDataHandler(loadNetworkTF, taskManager);
		GarudaEventHandler connectionHandler = new ConnectionEstablishedHandler(loadNetworkTF, taskManager);
		GarudaEventHandler loadGadgetHandler = new LoadGadgetHandler(taskManager);
		
		registerAllServices(bc, loadDataHandler, new Properties());
		registerAllServices(bc, connectionHandler, new Properties());
		registerAllServices(bc, loadGadgetHandler, new Properties());
		
		registerServiceListener(bc,changeListener,"registerHandler","deregisterHandler", GarudaEventHandler.class);
		
	}
}
