import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {
    private static MenuController instance;
    private final Scanner sc;

    private MenuController(){
        this.sc = new Scanner(System.in);
    }

    public static MenuController getInstance() {
        if (MenuController.instance == null) {
            MenuController.instance = new MenuController();
        }
        return MenuController.instance;
    }

    public int input(){
        int input = 0;
        try {
            input = sc.nextInt();
        }catch (InputMismatchException e){
            System.out.println("It's not a number! Try again.");
        }
        return input;
    }

    public <T extends Enum<T>> void printEnum(String text, Class<T> enumType){
        System.out.println(text);
        int optionNumber = 1;
        for(T constant : enumType.getEnumConstants()){
            System.out.println("" + optionNumber++ + ". " + constant);
        }
    }

    public boolean validateInput(int upperBound, int userInput){
        if(userInput < 0 || userInput > upperBound){
            System.out.println("There is no such an option");
            return false;
        }
        return true;
    }
}
