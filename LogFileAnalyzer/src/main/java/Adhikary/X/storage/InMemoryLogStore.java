package Adhikary.X.storage;

import Adhikary.X.model.LogEntry;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class InMemoryLogStore {

	private final ConcurrentLinkedQueue<LogEntry> logEntries
			= new ConcurrentLinkedQueue<>();

	private final ConcurrentHashMap<Path, ConcurrentLinkedQueue<LogEntry>> groupedByPathMap
			= new ConcurrentHashMap<>();

	private final ConcurrentHashMap<String,ConcurrentHashMap<Path,ConcurrentLinkedQueue<LogEntry>>>  groupedByLevelMap
			= new ConcurrentHashMap<>();

	private final AtomicLong totalCount =
			new AtomicLong(0);

	private final AtomicReference<LocalDateTime> lastUpdated
			= new AtomicReference<>(LocalDateTime.now());


	public void add(LogEntry entry)
	{

		Path path = entry.filePath();
		String level = entry.logLevel();
		logEntries.offer(entry);

		// In case there is no entry for a path in groupedByPathMap then the following object is used as value
		ConcurrentLinkedQueue<LogEntry> latestPathQueue = new ConcurrentLinkedQueue<>();
		latestPathQueue.offer(entry);
		groupedByPathMap.merge(path,latestPathQueue,(old,latest)->{

			old.offer(entry);
			return old;
		});




		//In case there is no entry(key ,value pair ) in groupedByLevelMap then the following object is used as value
		ConcurrentHashMap<Path,ConcurrentLinkedQueue<LogEntry>> latestLevelMap = new ConcurrentHashMap<>();
		latestLevelMap.put(path,latestPathQueue);
		groupedByLevelMap.merge(level,latestLevelMap,(old,latest)->{

			old.get(path).offer(entry);
			return old;

		});
	}

	public List<LogEntry> getAll()
	{
		return new ArrayList<>(logEntries);
	}



}
