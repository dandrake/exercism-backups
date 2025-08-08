import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogLine {

    private static Pattern levelPattern = Pattern.compile("\\[([A-Z]+)\\]:\\s+(\\S.*\\S)\\s*$");


    public static Matcher runMatcher(String logLine, String exceptionMessage)
    {
        Matcher matcher = levelPattern.matcher(logLine);
        if (matcher.find()) {
            return matcher;
        }
        throw new IllegalArgumentException(exceptionMessage);
    }

    private String encodedLevel;
    private String message;
    private LogLevel logLevel;

    public LogLine(String logLine) {
        Matcher matcher = runMatcher(logLine, "cannot find log level in line");
        this.encodedLevel = matcher.group(1);

        switch (encodedLevel) {
        case "TRC":
            this.logLevel = LogLevel.TRACE;
            break;
        case "DBG":
            this.logLevel = LogLevel.DEBUG;
            break;
        case "INF":
            this.logLevel = LogLevel.INFO;
            break;
        case "WRN":
            this.logLevel = LogLevel.WARNING;
            break;
        case "ERR":
            this.logLevel = LogLevel.ERROR;
            break;
        case "FTL":
            this.logLevel = LogLevel.FATAL;
            break;
        default:
            this.logLevel = LogLevel.UNKNOWN;
        }

        this.message = matcher.group(2);
    }

    public LogLevel getLogLevel() {
        return this.logLevel;
    }

    public String getOutputForShortLog() {
        return this.logLevel.getLogLevel() + ":" + this.message;
    }
}
