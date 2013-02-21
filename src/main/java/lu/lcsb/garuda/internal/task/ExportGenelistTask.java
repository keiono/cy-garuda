package lu.lcsb.garuda.internal.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.events.ColumnCreatedEvent;
import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;
import org.cytoscape.work.util.ListSingleSelection;

public class ExportGenelistTask extends AbstractTask {

	@Tunable(description = "Which column do you want to export as a Gene List?")
	public ListSingleSelection<String> nodeColumnList;
		
	
	public ExportGenelistTask(final CyApplicationManager appManager) {
		
		final CyNetwork currentNetwork = appManager.getCurrentNetwork();
		if(currentNetwork == null)
			throw new NullPointerException("Current network is not available.");
		
		
		final CyTable nodeTable = currentNetwork.getDefaultNodeTable();
		final Collection<CyColumn> columns = nodeTable.getColumns();
		
		final List<String> strColumns = new ArrayList<String>();
		
		for(final CyColumn column:columns) {
			final Class<?> type = column.getType();
			if(type == String.class)
				strColumns.add(column.getName());
		}
		nodeColumnList = new ListSingleSelection<String>(strColumns);
	}
	
	@Override
	public void run(TaskMonitor taskMonitor) throws Exception {
		// Create temp list
		System.out.println("Selected: " + nodeColumnList.getSelectedValue() );
		
		
		// Send event
	}

}
