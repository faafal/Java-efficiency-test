import java.util.ArrayList;

public class ResultManager {
    private static ResultManager instance;
    private static final String[] header = new String[]{
            "Data type","Collection Type","Number of instances","Creation time"
    };

    private ResultManager() {
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
                String.valueOf(test.getTestDuration()) + " ns"

        };
        for (int i = 0; i < results.length; i++) {
            System.out.printf("%-" + header[i].length() + "s | ", results[i]);
        }
        System.out.println();
        for (int i = 0; i < widthCounter; i++)
            System.out.print("-");
    }



}