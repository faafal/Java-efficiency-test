import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

public class efficiencyTest {
    public static void main(String[] args) {
        MenuController menu = MenuController.getInstance();
        TestParameters test = TestParameters.getInstance();

        int userInput = 0;
        do {
            System.out.println("Choose collection type");
            menu.printEnum(CollectionType.class);
            userInput = menu.input();
        } while (
                !menu.validateInput(Arrays.stream(CollectionType.class.getEnumConstants()).toArray().length, userInput)
        );
        test.setCollectionType(CollectionType.class.getEnumConstants()[userInput]);

        userInput = 0;
        do {
            System.out.println("Choose data type");
            menu.printEnum(DataType.class);
            userInput = menu.input();
        } while (
                !menu.validateInput(Arrays.stream(DataType.class.getEnumConstants()).toArray().length, userInput)
        );
        test.setDataType(DataType.class.getEnumConstants()[userInput]);

        userInput = 0;
        do {
            System.out.println("Enter number of instances");
            userInput = menu.input();
        } while (
                !menu.validateInput(Integer.MAX_VALUE, userInput)
        );
        test.setNumberOfObjects(userInput);
    }
}
