package Adhikary.X.monitor;

import Adhikary.X.model.LogEntry;

import java.util.Optional;
import java.util.concurrent.*;


class ProperBlockingTask extends RecursiveTask<Optional<LogEntry>>
{


	@Override
	public Optional<LogEntry> compute()
	{




	}



	private static class sleepBlocker implements ForkJoinPool.ManagedBlocker {
		private final long sleepTime;

		private final long startTime;

		/* Since ,
	we need to make the thread wait at least few seconds for modification to the
	log file before polling we will check how much time has elapsed from the
	starting of execution of this task , if enough time elapsed then no need to block
	which is why we have this startTime field to calculate the elapsed Time
	*/

		sleepBlocker(long sleepTime, long startTime)
		{
			this.sleepTime = sleepTime;
			this.startTime = System.currentTimeMillis();
		}

		@Override
		public boolean block() throws InterruptedException
		{
			Thread.sleep(sleepTime);
			return true;
		}

		@Override
		public boolean isReleasable()
		{
			return System.currentTimeMillis() - startTime > sleepTime;

		}
	}
}



public class ConcurrentLogMonitor {

	ExecutorService executor = Executors.newWorkStealingPool(12);






}
