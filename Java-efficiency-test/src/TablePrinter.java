public class TablePrinter {
    public static int[] computeColumnWidths(String[] header, String[][] results) {
        int columns = header.length;
        int[] widths = new int[columns];

        for (int i = 0; i < columns; i++) {
            int max = header[i].length();
            for (String[] row : results) {
                if (i < row.length) {
                    max = Math.max(max, row[i].length());
                }
            }
            widths[i] = max;
        }
        return widths;
    }

    private static void printSeparator(int[] widths) {
        for (int w : widths) {
            for (int i = 0; i < w + 3; i++) {
                System.out.print("-");
            }
        }
        System.out.println();
    }

    public static void printHeader(String[] header, int[] widths) {
        for (int i = 0; i < header.length; i++) {
            System.out.printf("%-" + widths[i] + "s | ", header[i]);
        }
        System.out.println();
        printSeparator(widths);
    }

    public static void printRows(String[][] results, int[] widths) {
        for (String[] row : results) {
            for (int i = 0; i < widths.length; i++) {
                String value = (i < row.length) ? row[i] : "";
                System.out.printf("%-" + widths[i] + "s | ", value);
            }
            System.out.println();
        }
        printSeparator(widths);
    }

    public static void printTable(String[] header, String[][] results) {
        int[] widths = computeColumnWidths(header, results);
        printHeader(header, widths);
        printRows(results, widths);
    }
}
