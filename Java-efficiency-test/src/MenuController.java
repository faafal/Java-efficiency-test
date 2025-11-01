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

    public void print(){}
}
