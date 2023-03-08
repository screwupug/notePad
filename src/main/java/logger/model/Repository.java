package logger.model;

import logger.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Repository implements LoggerRepository {
    private LoggerMapper loggerMapper;
    private LoggerFileOperation loggerFileOperation;

    public Repository(LoggerMapper loggerMapper, LoggerFileOperation loggerFileOperation) {
        this.loggerMapper = loggerMapper;
        this.loggerFileOperation = loggerFileOperation;
    }

    @Override
    public void createLog(Logger log) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy|HH:mm:ss");
        log.setDate(dateFormat.format(new Date()));
        loggerFileOperation.saveLine(loggerMapper.map(log));
    }
}
