package lu.lcsb.garuda.internal;

import static org.cytoscape.work.ServiceProperties.ENABLE_FOR;
import static org.cytoscape.work.ServiceProperties.ID;
import static org.cytoscape.work.ServiceProperties.MENU_GRAVITY;
import static org.cytoscape.work.ServiceProperties.PREFERRED_MENU;
import static org.cytoscape.work.ServiceProperties.TITLE;
import static org.cytoscape.work.ServiceProperties.TOOLTIP;

import java.util.Properties;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.platform.commons.net.GarudaConnectionNotInitializedException;
import lu.lcsb.garuda.GarudaEventHandler;
import lu.lcsb.garuda.internal.handlers.CompatibleGadgetsHandler;
import lu.lcsb.garuda.internal.handlers.ConnectionEstablishedHandler;
import lu.lcsb.garuda.internal.handlers.GadgetRegistrationSuccessHandler;
import lu.lcsb.garuda.internal.handlers.LoadDataHandler;
import lu.lcsb.garuda.internal.handlers.LoadGadgetHandler;
import lu.lcsb.garuda.internal.handlers.SendDataSuccessHandler;
import lu.lcsb.garuda.internal.handlers.errors.ConnectionNotInitializedHandler;
import lu.lcsb.garuda.internal.handlers.errors.GadgetRegistrationErrorHandler;
import lu.lcsb.garuda.internal.handlers.errors.GarudaCoreConnTerminatedHandler;
import lu.lcsb.garuda.internal.handlers.errors.GarudaCoreErrorHandler;
import lu.lcsb.garuda.internal.handlers.errors.SendDataErrorHandler;
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
		final CyApplicationManager appManager = getService(bc, CyApplicationManager.class);

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

		GarudaEventHandler connectionHandler = new ConnectionEstablishedHandler(taskManager);
		GarudaEventHandler loadGadgetHandler = new LoadGadgetHandler(taskManager);
		GarudaEventHandler connectionNotInitHandler = new ConnectionNotInitializedHandler(taskManager);
		GarudaEventHandler gadgetRegSuccessHandler = new GadgetRegistrationSuccessHandler(taskManager);
		
		GarudaEventHandler gadgetRegFailedHandler = new GadgetRegistrationErrorHandler(taskManager, backendGaurda);
		GarudaEventHandler compatibleGadgetsHandler = new CompatibleGadgetsHandler(taskManager, backendGaurda);
		GarudaEventHandler sendDataSuccessHandler = new SendDataSuccessHandler(taskManager, backendGaurda);
		GarudaEventHandler sendDataErrorHandler = new SendDataErrorHandler(taskManager, backendGaurda);
		GarudaEventHandler garudaCoreErrorHandler = new GarudaCoreErrorHandler(taskManager, backendGaurda);
		GarudaEventHandler garudaCoreConnTerminHandler = new GarudaCoreConnTerminatedHandler(taskManager, backendGaurda);

		// Register all services as OSGi service
		registerAllServices(bc, loadDataHandler, new Properties());
		registerAllServices(bc, connectionHandler, new Properties());
		registerAllServices(bc, loadGadgetHandler, new Properties());
		registerAllServices(bc, compatibleGadgetsHandler, new Properties());
		registerAllServices(bc, connectionNotInitHandler, new Properties());
		registerAllServices(bc, gadgetRegFailedHandler, new Properties());
		registerAllServices(bc, gadgetRegSuccessHandler, new Properties());
		registerAllServices(bc, sendDataErrorHandler, new Properties());
		registerAllServices(bc, garudaCoreErrorHandler, new Properties());
		registerAllServices(bc, garudaCoreConnTerminHandler, new Properties());
		registerAllServices(bc, sendDataSuccessHandler, new Properties());
		
		registerServiceListener(bc,changeListener,"registerHandler","deregisterHandler", GarudaEventHandler.class);
		

		// Tasks
		ExportGenelistTaskFactory exportGenelistTaskFactory = new ExportGenelistTaskFactory(appManager, backendGaurda);
		
		Properties exportGenelistTaskFactoryProps = new Properties();
		exportGenelistTaskFactoryProps.setProperty(ID,"exportGenelistTaskFactory");
		exportGenelistTaskFactoryProps.setProperty(PREFERRED_MENU,"Tools.Garuda.Send");
		exportGenelistTaskFactoryProps.setProperty(TITLE,"Gene List to...");
		exportGenelistTaskFactoryProps.setProperty(ENABLE_FOR,"network");
		exportGenelistTaskFactoryProps.setProperty(MENU_GRAVITY,"1.0");
		exportGenelistTaskFactoryProps.setProperty(TOOLTIP, "Export Node Table Column as Gene List");
		registerAllServices(bc, exportGenelistTaskFactory, exportGenelistTaskFactoryProps);
		
		// Some basic setup for backend...	
		try {
			backendGaurda.requestForLoadableGadgets("sbml", "xml");
			backendGaurda.requestForLoadableGadgets("genelist", "txt");
		} catch (GarudaConnectionNotInitializedException e) {
			logger.error("Could not connect to Garuda.", e);
		}
	}
}
