package Adhikary.X.storage;

import Adhikary.X.model.LogEntry;

import java.nio.file.Path;
import java.time.LocalDateTime;
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





}
