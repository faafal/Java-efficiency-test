import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class TestParameters {
    private static TestParameters instance;
    private DataType dataType;
    private CollectionType collectionType;
    private int numberOfObjects;

    private TestParameters() {
        this.collectionType = CollectionType.ARRAY_LIST;
        this.dataType = DataType.INTEGER;
        this.numberOfObjects = 100;
    }

    public static TestParameters getInstance() {
        if (instance == null) {
            instance = new TestParameters();
        }
        return instance;
    }

    public Collection<?> createCollection(){
        Supplier<?> dataSupplier = dataType.getDataSupplier();
        Collector<Object, ?, Collection<Object>> collectorSupplier = collectionType.getCollector();

        return Stream
                .generate(dataSupplier)
                .limit(this.numberOfObjects)
                .collect(collectorSupplier);
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
    public void setCollectionType(CollectionType collectionType) {
        this.collectionType = collectionType;
    }
    public void setNumberOfObjects(int numberOfObjects) {
        this.numberOfObjects = numberOfObjects;
    }

    public DataType getDataType() {
        return dataType;
    }
    public CollectionType getCollectionType() {
        return collectionType;
    }
    public int getNumberOfObjects() {
        return numberOfObjects;
    }
}