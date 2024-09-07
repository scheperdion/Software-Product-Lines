import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logging {
    static final Logger _logger = Logger.getLogger(Logging.class.getName());
    final DateTimeFormatter _dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public Logging(String fileName)
    {
        try {
            FileHandler _filehandler = new FileHandler(fileName, true);
            SimpleFormatter _formatter = new SimpleFormatter();
            _filehandler.setFormatter(_formatter);
            _logger.addHandler(_filehandler);
        }
        catch (IOException e)
        {
            _logger.severe(e.getMessage());
        }

    }

    private String getDateTime()
    {
        return  _dateTimeFormatter.format(LocalDateTime.now());
    }

    public void logInfo(String msg)
    {
        _logger.info(getDateTime() + msg);
    }

    public void logWarning(String msg)
    {
        _logger.warning(getDateTime() + msg);
    }

    public void logSevere(String msg)
    {
        _logger.severe(getDateTime() + msg);
    }
}
