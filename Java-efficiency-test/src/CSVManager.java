import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVManager {
    private static final String BASE_NAME = "results";
    private static final String EXT = ".csv";
    private String filePath;

    public CSVManager(){
        this.filePath = BASE_NAME + "_" + 1 + EXT;
        try {
            if(!checkHeader()) {
                createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createNewFile() throws IOException {
        for (int i = 1; i <= 10; i++) {
            this.filePath = BASE_NAME + "_" + i + EXT;
            File file = new File(this.filePath);

            if (file.exists()) {
                if (checkHeader()) {
                    return;
                }
            } else {
                file.createNewFile();

                String[] header = ResultManager.getHeader();
                StringBuilder br = new StringBuilder();
                for (int j = 0; j < header.length; j++) {
                    br.append(header[j]).append(j == header.length - 1 ? "" : ",");
                }
                br.append("\n");
                writeToCSV(br.toString());
                return;
            }
        }
        System.err.println("Couldn't create a results file");
        throw new RuntimeException("Failed to create file after 10 attempts.");
    }

    private boolean checkHeader() {
        File file = new File(filePath);
        if(file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                String[] splitLine;
                String[] header = ResultManager.getHeader();
                if ((line = reader.readLine()) != null && header.length == (splitLine = line.split(",")).length) {
                    for (int i = 0; i < header.length; i++) {
                        if (!header[i].equals(splitLine[i])) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            return false;
        }
        return false;
    }

    private void writeToCSV(String data){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))){
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void appendCsvLine(String[] row) {
        if(!checkHeader()) {
            try {
                createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < row.length; i++){
            sb.append(row[i]).append((i == row.length - 1? "\n" : ","));
        }
        writeToCSV(sb.toString());
    }

    public String[][] readAll(){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            String[][] data = new String[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                data[i] = lines.get(i).trim().split(",");
            }
            return data;
        }catch (IOException e){
            throw new RuntimeException("Błąd podczas czytania pliku CSV: " + e.getMessage(), e);
        }
    }
}
