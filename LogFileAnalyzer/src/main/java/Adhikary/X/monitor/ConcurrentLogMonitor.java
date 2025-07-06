package Adhikary.X.monitor;

import Adhikary.X.model.LogEntry;
import Adhikary.X.parser.LogParser;
import com.sun.jdi.BooleanValue;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Time;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.regex.Matcher;


class ProperBlockingTask extends RecursiveTask<ConcurrentHashMap<LogEntry, Time>>
{

	private final ConcurrentHashMap<Path, Long> fileOffsets;

	private final Path path;

	private final Long sleepTime;
	private final ConcurrentHashMap<Path, Boolean> runningFlags;

//	private final ArrayBlockingQueue<Optional<LogEntry>> tempQueue;

	private final ConcurrentHashMap<LogEntry, Time> tempMap;
	ProperBlockingTask(ConcurrentHashMap<Path,Long> fileOffsets, Path path ,Long sleepTime, ConcurrentHashMap<Path, Boolean> runningFlags , ConcurrentHashMap<LogEntry,Time> tempMap)
	{
		this.fileOffsets = fileOffsets;
		this.path = path;
		this.sleepTime = sleepTime;
		this.runningFlags = runningFlags;
		this.tempMap = tempMap;
	}

	@Override
	public ConcurrentHashMap<LogEntry,Time> compute()
	{


		if(Files.exists(path))
		{
			try (RandomAccessFile raf = new RandomAccessFile(path.toString(),"r")) {


				while (runningFlags.get(path))
				{
					if(!fileOffsets.containsKey(path))
					{
						fileOffsets.put(path,raf.length());
					}
					raf.seek(fileOffsets.get(path));

					Optional<String> line = Optional.ofNullable(raf.readLine());

					line.ifPresentOrElse(

							(lineValue)->{


								if(LogParser.parseLine(lineValue,path).isPresent() )
								{
									tempMap.put(LogParser.parseLine(lineValue,path).get(),new Time(System.currentTimeMillis()));

									try {
										fileOffsets.put(path,raf.getFilePointer());
									} catch (IOException e) {
										throw new RuntimeException(e);
									}

								}

								try {
									fileOffsets.put(path, raf.getFilePointer());
								}catch(IOException e)
								{
									throw new RuntimeException(e);
								}


							},()->{

							}
					);

					ForkJoinPool.managedBlock(new SleepBlocker(sleepTime));



				}


			}catch(IOException | InterruptedException e)
			{
				throw new RuntimeException(e);
			}
		}

		return tempMap;

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


	private final ConcurrentHashMap<Path,Boolean> runningFlags = new ConcurrentHashMap<>();

	private final ConcurrentHashMap<LogEntry,Time> tempMap = new ConcurrentHashMap<>();

	private final long sleepTime ;

	public ConcurrentLogMonitor(long sleepTime)
	{
		this.sleepTime = sleepTime;
	}




	ForkJoinPool executor = new ForkJoinPool(12);


	public void startMonitoring(Path path)
	{

		if(!Files.exists(path))
		{
			try {
				Files.createDirectory(path);
			}catch(IOException e)
			{
				throw new RuntimeException(e);
			}
		}


		executor.invoke(new ProperBlockingTask(fileOffsets,path,sleepTime,runningFlags,tempMap) );





	}




}
