package lu.lcsb.garuda.internal.task;

import jp.sbi.garuda.client.backend.GarudaClientBackend;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

public class ExportGenelistTaskFactory extends AbstractTaskFactory {

	private final CyApplicationManager appManager;
	private final GarudaClientBackend backend;
	
	public ExportGenelistTaskFactory(final CyApplicationManager appManager, final GarudaClientBackend backend) {
		this.appManager = appManager;
		this.backend = backend;
	}
	
	@Override
	public TaskIterator createTaskIterator() {
		return new TaskIterator(new ExportGenelistTask(appManager, backend));
	}

}
