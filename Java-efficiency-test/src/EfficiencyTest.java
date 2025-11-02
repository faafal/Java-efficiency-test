public class EfficiencyTest {
    public static void main(String[] args) {
        MenuController menu = MenuController.getInstance();
        TestParameters test = TestParameters.getInstance();
        ResultManager results = ResultManager.getInstance();

        test.setCollectionType(menu.chooseEnum("Choose collection type", CollectionType.class));
        test.setDataType(menu.chooseEnum("Choose data type", DataType.class));
        test.setNumberOfObjects(menu.chooseInt("Enter number of instances", Integer.MAX_VALUE));

        test.startTest();
        results.printResults(test);
    }
}
