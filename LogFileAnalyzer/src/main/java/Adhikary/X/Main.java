package Adhikary.X;

import Adhikary.X.Testing.TemporaryTesting;
import Adhikary.X.alert.*;
import Adhikary.X.consumer.LogConsumer;
import Adhikary.X.model.LogEntry;
import Adhikary.X.monitor.ConcurrentLogMonitor;
import Adhikary.X.parser.LogParser;
import Adhikary.X.storage.InMemoryLogStore;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class Main {

	public static void main(String... args) {



//	String log1Valid = "2025-07-03 11:22:33 ERROR [System] - Disk space critically low    ";
//
//	String log2Valid = "2025-07-03 11:23:10 INFO [Network] - Connection established successfully   ";
//
//	String log3Valid = "2025-07-03 11:24:01 WARN [Security] - Unauthorized login attempt @ 192.168.0.101";
//
//	String log4Invalid = "2025-07-03 11:25:44 ERROR Security - Missing source brackets";
//
//	String log5Invalid = "Jul 3, 2025 11:25:44 ERROR [System] - Invalid timestamp format";
//
//	String log6Invalid = "";
//
//	String log7Valid = "   2025-07-03 11:26:01  INFO   [UserModule]   -    User profile updated   ";
//
//
//	Optional<LogEntry> logEntry1 = LogParser.parseLine(log1Valid, Path.of("").toAbsolutePath());
//
//	Optional<LogEntry> logEntry2 = LogParser.parseLine(log2Valid, Path.of("").toAbsolutePath());
//
//	Optional<LogEntry> logEntry3 = LogParser.parseLine(log3Valid, Path.of("").toAbsolutePath());
//
//	Optional<LogEntry> logEntry4 = LogParser.parseLine(log4Invalid, Path.of("").toAbsolutePath());
//
//	Optional<LogEntry> logEntry5 = LogParser.parseLine(log5Invalid, Path.of("").toAbsolutePath());
//
//	Optional<LogEntry> logEntry6 = LogParser.parseLine(log6Invalid, Path.of("").toAbsolutePath());
//
//	Optional<LogEntry> logEntry7 = LogParser.parseLine(log7Valid, Path.of("").toAbsolutePath());
//
//
//	System.out.println(logEntry1.orElse(null));
//	System.out.println(logEntry2.orElse(null));
//	System.out.println(logEntry3.orElse(null));
//	System.out.println(logEntry4.orElse(null));
//	System.out.println(logEntry5.orElse(null));
//	System.out.println(logEntry6.orElse(null));
//	System.out.println(logEntry7.orElse(null));
////	System.out.println(logEntry4.orElse(null));
//


		String log8Valid = "2025-07-06 22:44:58 WARNING [SYSTEM] - DEMOMESSAGE2";


		Optional<LogEntry> logEntry8 = LogParser.parseLine(log8Valid,Path.of("BishalAppLogs.txt"));
		System.out.println(logEntry8.orElse(null));


		System.out.println("-".repeat(50));










		ExecutorService executor = Executors.newCachedThreadPool();

		InMemoryLogStore logStore1 = new InMemoryLogStore();



		List<Runnable> tasksList = List.of(()->TemporaryTesting.writeToFile(Path.of("BishalAppLogs.txt"),Duration.ofSeconds(10)),()->{

			TemporaryTesting.writeToFile(Path.of("App2Log.txt"),Duration.ofSeconds(10));

				}  );


		for(Runnable task : tasksList)
		{
			executor.execute(task);
		}

		LogConsumer printToConsoleConsumer  = (entry)->{
			System.out.println(entry);
		};
		LogConsumer addToStoreConsumer = (entry)->{
			logStore1.add(entry);
		};

		// First argument timeLimit determines how long each thread can run in seconds , second argument sleepTime is the thread sleep time before each polling from file in Milliseconds
		ConcurrentLogMonitor monitor1 = new ConcurrentLogMonitor(10,500L,printToConsoleConsumer.andThen(addToStoreConsumer));


		 monitor1.startMonitoring(Path.of("BishalAppLogs.txt"));
		 monitor1.startMonitoring(Path.of("App2Log.txt"));

		 ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		 AlertEngine alertEngine = new AlertEngine(logStore1);
		 AlertRule spikeInSevereRule = new SpikeInSevereRule(4,Duration.ofSeconds(5));
		 AlertNotifier consoleAlertNotifier = new ConsoleAlertNotifier();
		 alertEngine.registerRule(spikeInSevereRule,consoleAlertNotifier);


		 Runnable runAlertEngine = ()->{
			 alertEngine.evaluateAlerts();
		 };

		 scheduledExecutor.scheduleAtFixedRate(runAlertEngine,3L,1L, TimeUnit.SECONDS);


		 executor.shutdown();
		 try {
			 executor.awaitTermination(15, TimeUnit.SECONDS);
		 }catch (InterruptedException e)
		 {
			 throw new RuntimeException(e);
		 }

		 scheduledExecutor.shutdown();

		try {
			Thread.currentThread().sleep(12000);
		}catch(InterruptedException e)
		{
			throw new RuntimeException(e);
		}
		System.out.println("-".repeat(100));

		System.out.println("\n".repeat(5));

		System.out.println(" total Count of Entries in InMemoryLogStore : " + logStore1.totalCount());

//		logStore1.getAll().forEach((entry)->{
//			System.out.println(entry);
//		});


		long pathMapTotalSize = logStore1.getPathMap().values()
						.parallelStream()
								.flatMap((e)->{
								return	e.stream();
								})
										.count();

		long levelMapTotalSize = logStore1.getLevelMap().values()
						.parallelStream()
								.flatMap((e)->{
									return e.stream();
								})
										.count();


		System.out.println(" \n \n \n path map total log count : " + pathMapTotalSize + " levelMap total log count : " + levelMapTotalSize);

		System.out.println("SEVERE List : " );
		logStore1.getByLevel("severe ").forEach(System.out::println);

		System.out.println("App2log.txt List: ");
		logStore1.getByFile(Path.of("App2Log.txt")).forEach(System.out::println);

		System.out.println("-".repeat(100));
		logStore1.getRecent(Duration.ofSeconds(5)).forEach(System.out::println);

		System.out.println("lastUpdate value on logStore1: " + logStore1.getLastUpdated());

}

}
