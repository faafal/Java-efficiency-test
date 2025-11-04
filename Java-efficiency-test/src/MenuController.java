import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {
    private static MenuController instance;
    private final Scanner sc;

    private MenuController() {
        this.sc = new Scanner(System.in);
    }

    public static MenuController getInstance() {
        if (MenuController.instance == null) {
            MenuController.instance = new MenuController();
        }
        return MenuController.instance;
    }

    public int input() {
        int input = -1;
        try {
            input = sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
        }
        return input;
    }

    public long inputLong() {
        long input = -1;
        try {
            input = sc.nextLong();
        } catch (InputMismatchException e) {
            sc.nextLine();
        }
        return input;
    }

    public <T extends Enum<T>> void printEnum(String text, Class<T> enumType) {
        System.out.println(text);
        int optionNumber = 1;
        for (T constant : enumType.getEnumConstants()) {
            System.out.println("" + optionNumber++ + ". " + constant);
        }
    }

    public boolean validateInput(long upperBound, long userInput) {
        return userInput >= 0 && userInput < upperBound;
    }

    public <T extends Enum<T>> T chooseEnum(String message, Class<T> enumType) {
        T[] values = enumType.getEnumConstants();
        int choice;
        boolean invalid;
        do {
            printEnum(message, enumType);
            choice = input() - 1;
            invalid = !validateInput(values.length, choice);
            if (invalid) {
                System.out.println("There is no such option");
            }
        } while (invalid);
        return values[choice];
    }

    public long chooseNumber(String message, long max) {
        long value;
        boolean invalid;
        do {
            System.out.println(message);
            value = inputLong();
            invalid = !validateInput(max, value);
            if (invalid) {
                System.out.println("Invalid number, try again!");
            }
        } while (invalid);
        return value;
    }
}
