import java.util.Arrays;

public class ResultManager {
    private static ResultManager instance;
    private final CSVManager csvManager;
    private static final String[] header = new String[]{
            "Data type","Collection Type","Number of instances","Creation time"
    };

    private ResultManager() {
        this.csvManager = new CSVManager();
    }

    public static ResultManager getInstance() {
        if (ResultManager.instance == null) {
            ResultManager.instance = new ResultManager();
        }
        return ResultManager.instance;
    }

    public void printAll(){
        String[][] data = csvManager.readAll();

        String[][] rows = data;
        if (data.length > 0 && data[0].length == header.length && Arrays.equals(data[0], header)) {
            rows = Arrays.copyOfRange(data, 1, data.length);
        }

        TablePrinter.printTable(header, rows);
    }

    public void saveResults(TestParameters test){
        String[] row = buildRow(test);
        csvManager.appendCsvLine(row);
    }

    private String[] buildRow(TestParameters test) {
        long durationNs = test.getTestDuration();
        String formattedDuration;

        if (durationNs < 1_000) {
            formattedDuration = durationNs + " ns";
        } else if (durationNs < 1_000_000) {
            formattedDuration = String.format("%.3f µs", durationNs / 1_000.0);
        } else if (durationNs < 1_000_000_000) {
            formattedDuration = String.format("%.3f ms", durationNs / 1_000_000.0);
        } else if (durationNs < 60_000_000_000L) {
            formattedDuration = String.format("%.3f s", durationNs / 1_000_000_000.0);
        } else if (durationNs < 3_600_000_000_000L) {
            formattedDuration = String.format("%.3f min", durationNs / 60_000_000_000.0);
        } else {
            formattedDuration = String.format("%.3f h", durationNs / 3_600_000_000_000.0);
        }

        return new String[]{
                String.valueOf(test.getDataType()),
                String.valueOf(test.getCollectionType()),
                String.valueOf(test.getNumberOfObjects()),
                formattedDuration
        };
    }

    public static String[] getHeader(){
        return header;
    }
}