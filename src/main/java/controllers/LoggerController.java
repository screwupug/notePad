package controllers;

import logger.Logger;
import logger.model.LoggerRepository;

public class LoggerController {
    private LoggerRepository repository;

    public LoggerController(LoggerRepository repository) {
        this.repository = repository;
    }

    public void createLog(Logger log) {
        repository.createLog(log);
    }
}
