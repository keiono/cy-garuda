package lu.lcsb.garuda.internal;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import jp.sbi.garuda.client.backend.BackendNotInitializedException;
import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import jp.sbi.garuda.platform.commons.exception.NetworkException;
import jp.sbi.garuda.platform.commons.net.GarudaConnectionNotInitializedException;
import lu.lcsb.garuda.internal.task.RegisterGarudaTaskFactory;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.work.swing.DialogTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates a new menu item under Apps menu section.
 * 
 */
public class GarudaRegister extends AbstractCyAction implements PropertyChangeListener {

	private static final Logger logger = LoggerFactory.getLogger(GarudaRegister.class);
	private static final long serialVersionUID = 5992031723836564429L;

	private final GarudaClientBackend backend;

	private final DialogTaskManager taskManager;

	public GarudaRegister(CyApplicationManager cyApplicationManager, final String menuTitle,
			final GarudaClientBackend backendGaurda, final DialogTaskManager taskManager) {
		super(menuTitle, cyApplicationManager, null, null);
		this.backend = backendGaurda;
		this.taskManager = taskManager;

		setPreferredMenu("Tools");
	}

	public void actionPerformed(ActionEvent e) {

		// Write your own function here.
		logger.info("Registring CyGaruda...");

		

		backend.addGarudaChangeListener(this);
		try {
			backend.initialize();
		} catch (GarudaConnectionNotInitializedException err) {
			logger.error("Could not initialize GarudaClientBackend.", err);
		}

		String command = GarudaRegister.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String folder = new File(command).getParent() + "/";

		try {
			command = URLDecoder.decode(command, "UTF-8");
		} catch (UnsupportedEncodingException er) {
			er.printStackTrace();
		}

		command = command.substring(1);

		String os_name = System.getProperty("os.name").toLowerCase();

		if (os_name.indexOf("mac") >= 0) {
			command = "open \"/" + command + "\"";
		} else {
			command = "cmd /c start javaw.exe -jar \"" + command + "\"";
		}

		command = "cmd.exe /c start	\"C:/Users/christophe.trefois/workspace/Cyto3_AppDev/target/cytoscape.bat\"";

		System.out.println(folder);
		try {
			backend.registerGadgetToGaruda(command);
		} catch (NetworkException e1) {
			e1.printStackTrace();
		} catch (BackendNotInitializedException e1) {
			e1.printStackTrace();
		} catch (GarudaConnectionNotInitializedException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt instanceof GarudaBackendPropertyChangeEvent) {
			GarudaBackendPropertyChangeEvent garudaPropertyEvt = (GarudaBackendPropertyChangeEvent) evt;
			System.out.println("Received Property Change Call");
		}
	}
}
