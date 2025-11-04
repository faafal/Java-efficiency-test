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

    public void printResults(TestParameters test){
        int widthCounter = 0;
        for (String word : header) {
            System.out.printf("%-" + word.length() + "s | ", word);
            widthCounter += word.length() + 3;
        }
        System.out.println();
        for (int i = 0; i < widthCounter; i++)
            System.out.print("-");
        System.out.println();

        String[] results = {
                test.getDataType().toString(),
                test.getCollectionType().toString(),
                String.valueOf(test.getNumberOfObjects()),
                test.getTestDuration() + " ns"
        };
        for (int i = 0; i < results.length; i++) {
            System.out.printf("%-" + header[i].length() + "s | ", results[i]);
        }
        System.out.println();
        for (int i = 0; i < widthCounter; i++)
            System.out.print("-");
        System.out.println();
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