public class efficiencyTest {
    public static void main(String[] args) {
        MenuController menu = MenuController.getInstance();
        TestParameters test = TestParameters.getInstance();
        CollectionType[] collectionsTypes = CollectionType.class.getEnumConstants();
        DataType[] dataTypes = DataType.class.getEnumConstants();

        int userInput = 0;

        do {menu.printEnum("Choose collection type", CollectionType.class);
        } while ( !menu.validateInput(collectionsTypes.length, userInput = menu.input() ) );
        test.setCollectionType(collectionsTypes[userInput]);

        do {menu.printEnum("Choose data type", DataType.class);
        } while ( !menu.validateInput(dataTypes.length, userInput = menu.input()) );
        test.setDataType(dataTypes[userInput]);

        do {System.out.println("Enter number of instances");
        } while (!menu.validateInput(Integer.MAX_VALUE, userInput = menu.input()));
        test.setNumberOfObjects(userInput);
        
    }
}
