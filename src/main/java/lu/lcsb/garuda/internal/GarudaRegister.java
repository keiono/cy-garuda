package lu.lcsb.garuda.internal;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.swing.JOptionPane;

import jp.sbi.garuda.client.backend.BackendNotInitializedException;
import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import jp.sbi.garuda.platform.commons.Gadget;
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
	private GarudaClientBackend backend;

	public GarudaRegister(CyApplicationManager cyApplicationManager, final String menuTitle) {
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Garuda");
		
	}

	public void actionPerformed(ActionEvent e) {

		// Write your own function here.
		JOptionPane.showMessageDialog(null, "Registering Cytoscape-Garuda");
		GarudaLauncher garudaLauncher = GarudaLauncher.getInstance() ;
		backend = garudaLauncher.getBackend();
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
		command = "lala";
		
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
			
			// Handle Error Messages that will be sent from the Core or the Backend
			if(garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.GOT_ERRORS_PROPERTY_CHANGE_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}
			
			// Handle the incoming list of compatible gadgets for a previously sent request (see next section for more details)
			if(garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.GOT_GADGETS_PROPERTY_CHANGE_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
				List<Gadget> listOfCompatibleGadgets = backend.getCompatibleGadgetList();
				// rest of the code to handle the list of compatible gadgets
			}
			
			// Handle the notification for the connection of the backend to the Core
			if(garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.GADGET_CONNECTION_ESTABLISHED_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}
			
			// Handle the notification for the disconnection from the Core
			if(garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.CONNECTION_TERMINATED_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}
			
			// Handle the case that there is no proper initialization of the Backend
			if(garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.CONNECTION_NOT_INITIALIZED_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}
			
			// Handle the response that deals with a sent data request successfully
			if(garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.SENT_DATA_RECEIVED_RESPONSE)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}
			
			// Handle the response that deals with a sent data request unsuccessfully
			if(garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.SENT_DATA_RECEIVED_RESPONSE_ERROR)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}
			
			// Handle the arrival of incoming data from another gadget through the Core
			if(garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.LOAD_DATA_PROPERTY_CHANGE_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
				Gadget theOriginGadget = (Gadget)garudaPropertyEvt.getFirstProperty();
				String theFilePath = (String)garudaPropertyEvt.getSecondProperty();
				// rest of code to handle incoming file
			}
			
			// Handle the request for the loading of a gadget inside this gadget (plugin loading)
			if(garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.LOAD_GADGET_PROPERTY_CHANGE_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
				Gadget loadableGadget = (Gadget)garudaPropertyEvt.getFirstProperty();
				String launchPathofGadget = (String)garudaPropertyEvt.getSecondProperty();
				// rest of code to handle the loading of gadget
			}

			// Handle the case that the registration process has some kind of error
			if(garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.GADGET_REGISTRATION_ERROR_ID)) {
				System.out.println("Registration Failed");
				System.out.println(garudaPropertyEvt.getPropertyName().toString());
			}
			
			// Handle the case that the registration process has been successful
			if(garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.GADGET_REGISTRERED_ID)) {
				System.out.println("Registration Successful.");
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}
		}
	}
}
