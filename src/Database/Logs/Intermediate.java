package Database.Logs;

public class Intermediate implements Log{
    Logs globalLogsObject = new Logs();
    @Override
    public Logs[] getLogs(String byFilter, String onFilter) {
        return globalLogsObject.getLogs(byFilter, onFilter);
    }
}
