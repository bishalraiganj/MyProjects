package Adhikary.X.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LogEntry(LocalDateTime timestamp,String logLevel,String source,String message,String fileName) {



}
