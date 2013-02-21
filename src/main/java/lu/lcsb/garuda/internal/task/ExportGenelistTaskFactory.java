package lu.lcsb.garuda.internal.task;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

public class ExportGenelistTaskFactory extends AbstractTaskFactory {

	private final CyApplicationManager appManager;
	
	public ExportGenelistTaskFactory(final CyApplicationManager appManager) {
		this.appManager = appManager;
	}
	
	@Override
	public TaskIterator createTaskIterator() {
		return new TaskIterator(new ExportGenelistTask(appManager));
	}

}
