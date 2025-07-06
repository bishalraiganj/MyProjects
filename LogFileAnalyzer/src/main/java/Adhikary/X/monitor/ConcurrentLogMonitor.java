package Adhikary.X.monitor;

import Adhikary.X.model.LogEntry;

import java.util.Optional;
import java.util.concurrent.*;


class ProperBlockingTask extends RecursiveTask<Optional<LogEntry>> {


	@Override
	public Optional<LogEntry> compute()
	{



	}



}

class sleepBlocker extends ForkJoinPool.ManagedBlocker
{
	private final long sleepTime;

	private final long startTime;




}


public class ConcurrentLogMonitor {

	ExecutorService executor = Executors.newWorkStealingPool(12);






}
