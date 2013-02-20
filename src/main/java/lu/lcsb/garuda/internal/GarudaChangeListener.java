package lu.lcsb.garuda.internal;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.client.backend.listeners.GarudaBackendPropertyChangeEvent;
import jp.sbi.garuda.platform.commons.Gadget;

public class GarudaChangeListener implements PropertyChangeListener {

	private final GarudaClientBackend backend;
	
	public GarudaChangeListener(final GarudaClientBackend backend) {
		this.backend = backend;
	}
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt instanceof GarudaBackendPropertyChangeEvent) {
			GarudaBackendPropertyChangeEvent garudaPropertyEvt = (GarudaBackendPropertyChangeEvent) evt;
			System.out.println("Received Property Change Call");

			// Handle Error Messages that will be sent from the Core or the
			// Backend
			if (garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.GOT_ERRORS_PROPERTY_CHANGE_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}

			// Handle the incoming list of compatible gadgets for a previously
			// sent request (see next section for more details)
			if (garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.GOT_GADGETS_PROPERTY_CHANGE_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
				List<Gadget> listOfCompatibleGadgets = backend.getCompatibleGadgetList();
				// rest of the code to handle the list of compatible gadgets
			}

			// Handle the notification for the connection of the backend to the
			// Core
			if (garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.GADGET_CONNECTION_ESTABLISHED_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}

			// Handle the notification for the disconnection from the Core
			if (garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.CONNECTION_TERMINATED_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}

			// Handle the case that there is no proper initialization of the
			// Backend
			if (garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.CONNECTION_NOT_INITIALIZED_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}

			// Handle the response that deals with a sent data request
			// successfully
			if (garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.SENT_DATA_RECEIVED_RESPONSE)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}

			// Handle the response that deals with a sent data request
			// unsuccessfully
			if (garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.SENT_DATA_RECEIVED_RESPONSE_ERROR)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}

			// Handle the arrival of incoming data from another gadget through
			// the Core
			if (garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.LOAD_DATA_PROPERTY_CHANGE_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
				Gadget theOriginGadget = (Gadget) garudaPropertyEvt.getFirstProperty();
				String theFilePath = (String) garudaPropertyEvt.getSecondProperty();
				// rest of code to handle incoming file
			}

			// Handle the request for the loading of a gadget inside this gadget
			// (plugin loading)
			if (garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.LOAD_GADGET_PROPERTY_CHANGE_ID)) {
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
				Gadget loadableGadget = (Gadget) garudaPropertyEvt.getFirstProperty();
				String launchPathofGadget = (String) garudaPropertyEvt.getSecondProperty();
				// rest of code to handle the loading of gadget
			}

			// Handle the case that the registration process has some kind of
			// error
			if (garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.GADGET_REGISTRATION_ERROR_ID)) {
				System.out.println("Registration Failed");
				System.out.println(garudaPropertyEvt.getPropertyName().toString());
			}

			// Handle the case that the registration process has been successful
			if (garudaPropertyEvt.getPropertyName().equals(GarudaClientBackend.GADGET_REGISTRERED_ID)) {
				System.out.println("Registration Successful.");
				System.out.println(garudaPropertyEvt.getFirstProperty().toString());
			}
		}
	}

}
