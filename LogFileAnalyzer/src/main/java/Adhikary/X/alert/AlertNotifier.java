package Adhikary.X.alert;

import Adhikary.X.model.LogEntry;

import java.util.List;

public interface AlertNotifier {

	void  notify(AlertRule rule, List<LogEntry> matchingLogs);



}
