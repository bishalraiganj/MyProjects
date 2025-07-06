package Adhikary.X;

import Adhikary.X.Testing.TemporaryTesting;
import Adhikary.X.model.LogEntry;
import Adhikary.X.monitor.ConcurrentLogMonitor;
import Adhikary.X.parser.LogParser;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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




		List<Runnable> tasksList = List.of(()->TemporaryTesting.writeToFile(Path.of("BishalAppLogs.txt"),Duration.ofSeconds(25)),()->{

			TemporaryTesting.writeToFile(Path.of("App2Log.txt"),Duration.ofSeconds(15));

				}  );


		for(Runnable task : tasksList)
		{
			executor.execute(task);
		}

		ConcurrentLogMonitor monitor1 = new ConcurrentLogMonitor(25,900L,(logEntry)->{
			System.out.println(logEntry);
		});

		 monitor1.startMonitoring(Path.of("BishalAppLogs.txt"));
		 monitor1.startMonitoring(Path.of("App2Log.txt"));
		executor.shutdown();



//		TemporaryTesting.writeToFile(Path.of("BishalAppLogs.txt"), Duration.ofSeconds(30));

}

}
