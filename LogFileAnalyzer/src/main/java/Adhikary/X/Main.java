package Adhikary.X;

import Adhikary.X.model.LogEntry;
import Adhikary.X.parser.LogParser;

import java.nio.file.Path;
import java.util.Optional;

public class Main {

	public static void main(String... args) {



	String log1Valid = "2025-07-03 11:22:33 ERROR [System] - Disk space critically low";

	String log2Valid = "2025-07-03 11:23:10 INFO [Network] - Connection established successfully";

	String log3Valid = "2025-07-03 11:24:01 WARN [Security] - Unauthorized login attempt @ 192.168.0.101";

	String log4Invalid = "2025-07-03 11:25:44 ERROR Security - Missing source brackets";

	String log5Invalid = "Jul 3, 2025 11:25:44 ERROR [System] - Invalid timestamp format";

	String log6Invalid = "";

	String log7Valid = "   2025-07-03 11:26:01  INFO   [UserModule]   -    User profile updated   ";


	Optional<LogEntry> logEntry1 = LogParser.parseLine(log1Valid, Path.of("").toAbsolutePath());

	Optional<LogEntry> logEntry2 = LogParser.parseLine(log2Valid, Path.of("").toAbsolutePath());

	Optional<LogEntry> logEntry4 = LogParser.parseLine(log4Invalid, Path.of("").toAbsolutePath());

	System.out.println(logEntry1.orElse(null));
	System.out.println(logEntry2.orElse(null));
	System.out.println(logEntry4.orElse(null));

}

}
