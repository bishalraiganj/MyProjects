package Adhikary.X.storage;

import Adhikary.X.model.LogEntry;


import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class InMemoryLogStore {

	private final ConcurrentLinkedQueue<LogEntry> logEntries
			= new ConcurrentLinkedQueue<>();

	private final ConcurrentHashMap<Path, ConcurrentLinkedQueue<LogEntry>> groupedByPathMap
			= new ConcurrentHashMap<>();

	private final ConcurrentHashMap<String,ConcurrentLinkedQueue<LogEntry>>  groupedByLevelMap
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



//		groupedByPathMap.computeIfAbsent(path,(p)->{
//
//			ConcurrentLinkedQueue<LogEntry> queue =  new ConcurrentLinkedQueue<>();
//			queue.offer(entry);
//			return queue;
//		});

		groupedByPathMap.compute(path,(p,v)->{
			if (v == null)
			{
			ConcurrentLinkedQueue<LogEntry> 	queue = new ConcurrentLinkedQueue<>();
			queue.offer(entry);
			return queue;
			}
			v.offer(entry);
			return v;
		});



//			groupedByLevelMap.computeIfAbsent(level, (l) -> {
//
//				ConcurrentLinkedQueue<LogEntry> queue = new ConcurrentLinkedQueue<>();
//				queue.offer(entry);
//				return queue;
//			});


			groupedByLevelMap.compute(level, (l, v) -> {
			if(v == null)
			{
				ConcurrentLinkedQueue<LogEntry> queue = new ConcurrentLinkedQueue<>();
				queue.offer(entry);
				return queue;
			}
				v.offer(entry);
				return v;
			});





		totalCount.getAndIncrement();
	}

	public List<LogEntry> getAll()
	{
		return new ArrayList<>(logEntries);
	}

	public long totalCount()
	{
		return totalCount.get();
	}

	public HashMap<Path,ConcurrentLinkedQueue<LogEntry>> getPathMap()
	{
		return new HashMap<>(groupedByPathMap);
	}

	public HashMap<String,ConcurrentLinkedQueue<LogEntry>> getLevelMap()
	{
		return new HashMap<>(groupedByLevelMap);
	}

}
