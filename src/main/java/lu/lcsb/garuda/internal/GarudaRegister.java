package lu.lcsb.garuda.internal;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.swing.JOptionPane;

import jp.sbi.garuda.client.backend.BackendNotInitializedException;
import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import jp.sbi.garuda.platform.commons.exception.NetworkException;
import jp.sbi.garuda.platform.commons.net.GarudaConnectionNotInitializedException;

import org.apache.log4j.Logger;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class GarudaRegister extends AbstractCyAction implements PropertyChangeListener {

	private static final Logger LOG = Logger.getLogger(GarudaClientBackend.class);
	private static final long serialVersionUID = 5992031723836564429L;

	public GarudaRegister(CyApplicationManager cyApplicationManager, final String menuTitle) {
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Garuda");
		
	}

	public void actionPerformed(ActionEvent e) {

		// Write your own function here.
		JOptionPane.showMessageDialog(null, "Registering Cytoscape-Garuda");
		GarudaLauncher garudaLauncher = GarudaLauncher.INSTANCE;
		GarudaClientBackend backend = garudaLauncher.getBackend();
		backend.addGarudaChangeListener(this);
		try {
			backend.initialize();
		} catch (GarudaConnectionNotInitializedException err) {
			err.printStackTrace();
		}
		
        
    	String command = GarudaRegister.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    	String folder = new File ( command ).getParent() + "/" ;
    	
    	
    	try {
			command =URLDecoder.decode(command,"UTF-8");
		} catch (UnsupportedEncodingException er) {
			er.printStackTrace();
		}
    	
    	command = command.substring(1) ;
        
        String os_name = System.getProperty("os.name").toLowerCase();;
        
		
		if ( os_name.indexOf("mac") >= 0) {
			command = "open \"/" + command +"\"";
		}
		else {
			command = "cmd /c start javaw.exe -jar \"" + command + "\"" ;
		}
		
		command = "cmd.exe /c start	\"C:/Users/christophe.trefois/workspace/Cyto3_AppDev/target/cytoscape.bat\"";
		
		System.out.println ( folder ) ;
		try {
			backend.registerGadgetToGaruda(command);
		} catch (NetworkException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BackendNotInitializedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (GarudaConnectionNotInitializedException e1) {
			// TODO Auto-generated catch block
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
