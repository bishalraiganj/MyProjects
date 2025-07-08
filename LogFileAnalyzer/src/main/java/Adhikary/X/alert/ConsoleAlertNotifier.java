package Adhikary.X.alert;

import Adhikary.X.model.LogEntry;

import java.util.List;

public class ConsoleAlertNotifier implements AlertNotifier {


	@Override
	public void notify(AlertRule rule, List<LogEntry> matchingLogs)
	{
		System.out.println("\n\n Console Alert Triggered ! ");
		System.out.println("Rule: " + rule);
		System.out.println("Log count: " + matchingLogs.size());
		System.out.println("Window: " + rule.getWindow().toSeconds() + " seconds");
		System.out.println("Matching Log Entries : ");
		matchingLogs.forEach(logEntry->{
			System.out.println(logEntry);
		});
		System.out.println("!".repeat(100));

	}
}
