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
        return new String[]{
                String.valueOf(test.getDataType()),
                String.valueOf(test.getCollectionType()),
                String.valueOf(test.getNumberOfObjects()),
                test.getTestDuration() + " ns"
        };
    }

    public static String[] getHeader(){
        return header;
    }
}