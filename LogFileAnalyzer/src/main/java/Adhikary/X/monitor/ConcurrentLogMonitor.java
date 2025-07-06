package Adhikary.X.monitor;

import Adhikary.X.consumer.LogConsumer;
import Adhikary.X.model.LogEntry;
import Adhikary.X.parser.LogParser;
import com.sun.jdi.BooleanValue;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;


class ProperBlockingTask extends RecursiveTask<ConcurrentHashMap<LogEntry, Time>>
{

	private final ConcurrentHashMap<Path, Long> fileOffsets;

	private final Path path;

	private final Long sleepTime; // No need for atomicLong since we are not concurrently accessing or modifying
	private final ConcurrentHashMap<Path, AtomicBoolean> runningFlags;

//	private final ArrayBlockingQueue<Optional<LogEntry>> tempQueue;

//	private final ConcurrentHashMap<LogEntry, Time> tempMap;

	private final LogConsumer consumer;

	private final ConcurrentHashMap<String, LocalDateTime> processedFailedLogsMap = new ConcurrentHashMap<>();

	private final AtomicLong logTotalCount = new AtomicLong(0L);

	ProperBlockingTask(ConcurrentHashMap<Path,Long> fileOffsets, Path path ,Long sleepTime, ConcurrentHashMap<Path, AtomicBoolean> runningFlags ,LogConsumer consumer )
	{
		this.fileOffsets = fileOffsets;
		this.path = path;
		this.sleepTime = sleepTime;
		this.runningFlags = runningFlags;
//		this.tempMap = tempMap;
		this.consumer = consumer;
	}

	@Override
	public ConcurrentHashMap<LogEntry,Time> compute()
	{


		long sTime = System.currentTimeMillis();


			if (Files.exists(path)) {
				try (RandomAccessFile raf = new RandomAccessFile(path.toString(), "r")) {


//					if (!runningFlags.containsKey(path)) {
//						runningFlags.put(path, new AtomicBoolean(true));
//					}
					while (runningFlags.get(path).get() && System.currentTimeMillis() - sTime <= 90 * 1000) {
						if (!fileOffsets.containsKey(path)) {
							fileOffsets.put(path, raf.length());
						}
						raf.seek(fileOffsets.get(path));

						Optional<String> line = Optional.ofNullable(raf.readLine());

						line.ifPresentOrElse(

								(lineValue) -> {

									Optional<LogEntry> maybeLogEntry = LogParser.parseLine(lineValue, path);

									if (maybeLogEntry.isPresent()) { // Only on successful parsing returned optional instance will have a logEntry value
//									tempMap.put(maybeLogEntry.get(),new Time(System.currentTimeMillis()));


										logTotalCount.incrementAndGet();
										consumer.accept(maybeLogEntry.get());
//									try {
//										fileOffsets.put(path, raf.getFilePointer());
//									} catch (IOException e) {
//										throw new RuntimeException(e);
//									}

									}

									try {
										if(maybeLogEntry.isEmpty()) {
											processedFailedLogsMap.merge(lineValue, LocalDateTime.now(), (ol, n) -> LocalDateTime.now());
										}
										fileOffsets.put(path, raf.getFilePointer());
									} catch (IOException e) {
										throw new RuntimeException(e);
									}


								}, () -> {

								}
						);

						ForkJoinPool.managedBlock(new SleepBlocker(sleepTime));


					}


				} catch (IOException | InterruptedException e) {
					throw new RuntimeException(e);
				}
			}


		return null;

	}





	private static class SleepBlocker implements ForkJoinPool.ManagedBlocker {
		private final long sleepTime;

		private final long startTime;

		/* Since ,
	we need to make the thread wait at least few seconds for modification to the
	log file before polling we will check how much time has elapsed from the
	starting of execution of this task , if enough time elapsed then no need to block
	which is why we have this startTime field to calculate the elapsed Time
	*/

		SleepBlocker(long sleepTime)
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
			return System.currentTimeMillis() - startTime >= sleepTime;

		}
	}
}





public class ConcurrentLogMonitor {

	private final ConcurrentHashMap<Path,Long> fileOffsets = new ConcurrentHashMap<>();


	private final ConcurrentHashMap<Path, AtomicBoolean> runningFlags = new ConcurrentHashMap<>();

//	private final ConcurrentHashMap<LogEntry,Time> tempMap = new ConcurrentHashMap<>();

	private final long sleepTime ;

	private final LogConsumer consumer;



	public ConcurrentLogMonitor(long sleepTime, LogConsumer consumer)
	{
		this.sleepTime = sleepTime;
		this.consumer = consumer;
	}




	ForkJoinPool executor = new ForkJoinPool(12);


	public void startMonitoring(Path path)
	{

		runningFlags.putIfAbsent(path,new AtomicBoolean(true));
		if(!Files.exists(path) || !Files.isRegularFile(path))
		{
//			try {
//				Files.createFile(path);
//			}catch(IOException e)
//			{
//				throw new RuntimeException(e);
//			}

			throw new RuntimeException(" Cannot monitor nonexistent or invalid log file: " + path);
		}


		executor.submit(new ProperBlockingTask(fileOffsets,path,sleepTime,runningFlags,consumer) );





	}

	public void stopMonitoring(Path path)
	{
		AtomicBoolean runningFlag = runningFlags.get(path);
		if(runningFlag == null)
		{
			System.out.println("No running flag found for path: " + path);
		}
		else {

			runningFlags.get(path).set(false);
		}

	}




}
