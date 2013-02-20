package lu.lcsb.garuda.internal.task;

import jp.sbi.garuda.client.backend.GarudaClientBackend;

import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

public class RegisterGarudaTaskFactory extends AbstractTaskFactory {

	private final GarudaClientBackend backendGaurda;

	public RegisterGarudaTaskFactory(final GarudaClientBackend backendGaurda) {
		this.backendGaurda = backendGaurda;
	}

	@Override
	public TaskIterator createTaskIterator() {
		return new TaskIterator(new RegisterGarudaTask(backendGaurda));
	}

}
