package logger.model;

import logger.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mapper implements LoggerMapper {
    @Override
    public String map(Logger log) {
        return String.format("%s|%s\n", log.getDate(), log.getMessage());
    }
}
