import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class TestParameters {
    private static TestParameters instance;
    private DataType dataType;
    private CollectionType collectionType;
    private int numberOfObjects;
    private long testDuration;
    private Collection<?> collection;

    private TestParameters() {
        this.collectionType = CollectionType.ARRAY_LIST;
        this.dataType = DataType.INTEGER;
        this.numberOfObjects = 100;
        this.testDuration = 0;
    }

    public static TestParameters getInstance() {
        if (instance == null) {
            instance = new TestParameters();
        }
        return instance;
    }

    public void startTest() {
        Supplier<?> dataSupplier = dataType.getDataSupplier();
        Collector<Object, ?, Collection<Object>> collectorSupplier = collectionType.getCollector();
        long start = System.currentTimeMillis();
        this.collection = Stream
                .generate(dataSupplier)
                .limit(this.numberOfObjects)
                .collect(collectorSupplier);
        this.testDuration = System.currentTimeMillis() - start;
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
    public long getTestDuration() {
        return testDuration;
    }
}