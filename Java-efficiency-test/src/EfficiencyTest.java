import java.util.concurrent.ThreadLocalRandom;

public class EfficiencyTest {
    public static void main(String[] args) {
//        MenuController menu = MenuController.getInstance();
        TestParameters test = TestParameters.getInstance();
        ResultManager results = ResultManager.getInstance();


//        test.setCollectionType(menu.chooseEnum("Choose collection type", CollectionType.class));
//        test.setDataType(menu.chooseEnum("Choose data type", DataType.class));
//        test.setNumberOfObjects(menu.chooseInt("Enter number of instances", Integer.MAX_VALUE));

        for(int i = 0 ; i < 10; i++){
            test.setDataType(DataType.values()[ThreadLocalRandom.current().nextInt(DataType.values().length)]);
            test.setCollectionType(CollectionType.values()[ThreadLocalRandom.current().nextInt(CollectionType.values().length)]);
            test.setNumberOfObjects(ThreadLocalRandom.current().nextInt(1_000_000, 10_000_000));
            test.startTest();
            results.saveResults(test);
        }
        results.printAll();
    }
}
