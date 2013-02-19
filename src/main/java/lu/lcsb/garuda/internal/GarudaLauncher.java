package lu.lcsb.garuda.internal;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.sbi.garuda.platform.commons.FileFormat;

public class GarudaLauncher {

	private static final String SOFTWARE_ID = "ea2f21c0-7a3c-11e2-b92a-0800200c9a66" ;
	private static final String SOFTWARE_NAME = "CytoscapeGaruda" ;
	private static List<String> categories;
	private static String description;
	private static List<String> screenshots;
	private static String ICON_PATH ; 
	private static Color background ;
	
	public static void main (String[] args) {
		String command = GarudaLauncher.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    	
    	
    	String folder = new File ( command ).getParent() + "/" ;
		
		List<FileFormat> outffs , inffs ;
		
	   	outffs= new ArrayList<FileFormat>();
	    outffs.add(new FileFormat("xml", "sbml"));
	    outffs.add(new FileFormat("txt", "txt"));
	    //List of the inputs for this software
	    inffs = new ArrayList<FileFormat>();
	    inffs.add(new FileFormat("xml","sbml"));
	    inffs.add(new FileFormat("html","html"));
	    inffs.add(new FileFormat("txt","txt"));
	     
	    categories = new ArrayList<String>();
	    categories.add("Simulation") ;
	    categories.add("Optimization") ;
	    
	    description = "TSA Description";
	 	screenshots = new ArrayList<String>() ;
	 	
	 	
	 	ICON_PATH = folder + "TestSoftAico.png" ;
        screenshots.add(folder + "Screen1.png");
        screenshots.add(folder + "Screen2.png");
        screenshots.add(folder + "Screen3.png");
        
        background = new Color ( 57 , 156 , 206 );

		
		
		try {
			//NewTestSoftwareGUI frame = new NewTestSoftwareGUI( SOFTWARE_ID ,SOFTWARE_NAME , categories , description , outffs ,inffs , ICON_PATH , screenshots , background);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
