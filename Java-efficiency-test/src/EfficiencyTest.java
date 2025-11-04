import java.util.concurrent.ThreadLocalRandom;

public class EfficiencyTest {
    public static void main(String[] args) {
        MenuController menu = MenuController.getInstance();
        TestParameters test = TestParameters.getInstance();
        ResultManager results = ResultManager.getInstance();
        System.out.println(Long.MAX_VALUE);

        test.setCollectionType(menu.chooseEnum("Choose collection type", CollectionType.class));
        test.setDataType(menu.chooseEnum("Choose data type", DataType.class));
        test.setNumberOfObjects(menu.chooseNumber("Enter number of instances", Long.MAX_VALUE));

        test.startTest();
        results.saveResults(test);
        results.printAll();
    }
}
