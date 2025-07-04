package Adhikary.X.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record LogEntry(LocalDateTime timestamp, String logLevel, String source, String message, Path filePath) {



}
