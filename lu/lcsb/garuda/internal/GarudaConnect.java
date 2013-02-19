package lu.lcsb.garuda.internal;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.application.swing.AbstractCyAction;


/**
 * Creates a new menu item under Apps menu section.
 *
 */
public class GarudaConnect extends AbstractCyAction {

	public GarudaConnect(CyApplicationManager cyApplicationManager, final String menuTitle) {
		
		super(menuTitle, cyApplicationManager, null, null);
		setPreferredMenu("Garuda");
		
	}

	public void actionPerformed(ActionEvent e) {

		// Write your own function here.
		JOptionPane.showMessageDialog(null, "Hello Cytoscape Garuda");
		
	}
}
