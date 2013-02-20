package lu.lcsb.garuda.internal;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.sbi.garuda.client.backend.GarudaClientBackend;
import jp.sbi.garuda.platform.commons.FileFormat;
import jp.sbi.garuda.platform.commons.exception.NetworkException;

public class GarudaLauncher {
	
	public final static GarudaLauncher INSTANCE = new GarudaLauncher();
	private GarudaClientBackend backendGaurda;

	private static final String SOFTWARE_ID = "ea2f21c0-7a3c-11e2-b92a-0800200c9a66" ;
	private static final String SOFTWARE_NAME = "CyGaruda" ;
	private static final String PROVIDER = "LCSB";
	private static List<String> categories;
	private static String description;
	private static List<String> screenshots;
	private static String ICON_PATH ; 
	private static Color background ;
	private static List<FileFormat> outffs , inffs ;
	
	private GarudaLauncher() {
		String command = GarudaLauncher.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    	
    	String folder = new File ( command ).getParent() + "/" ;
		
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
	 	
	 	
	 	ICON_PATH = folder + "cySmall.png" ;
        screenshots.add(folder + "cyGar_ss1.png");
        //screenshots.add(folder + "Screen2.png");
        //screenshots.add(folder + "Screen3.png");
        
        background = new Color ( 57 , 156 , 206 );

    /*    try {
        	backendGaurda = new GarudaClientBackend(SOFTWARE_ID, SOFTWARE_NAME, ICON_PATH, outffs, inffs, categories, provider, description, screenshots);
        } catch (NetworkException e) {
        	e.printStackTrace();
        }*/
	}
	
	public GarudaClientBackend getBackend() {
		try {
			if ( backendGaurda == null) {
				backendGaurda = new GarudaClientBackend(SOFTWARE_ID, SOFTWARE_NAME, ICON_PATH, outffs, inffs, categories, PROVIDER, description, screenshots);
			}
		} catch (NetworkException e) {
			e.printStackTrace();
		}
		return backendGaurda;
	}

}
