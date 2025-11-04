import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class TestParameters {
    private static TestParameters instance;
    private DataType dataType;
    private CollectionType collectionType;
    private long numberOfObjects;
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
        if (numberOfObjects <= 0) {
            throw new IllegalArgumentException("Number of objects must be positive.");
        }

        Supplier<?> dataSupplier = dataType.getDataSupplier();
        Collector<Object, ?, Collection<Object>> collectorSupplier = collectionType.getCollector();
        if (this.numberOfObjects > 20_000_000) {
            startTestChunked();
            return;
        }
        long start = System.nanoTime();
        this.collection = Stream
                .generate(dataSupplier)
                .limit(this.numberOfObjects)
                .collect(collectorSupplier);
        this.testDuration = System.nanoTime() - start;
    }

    private void startTestChunked() {
        Supplier<?> dataSupplier = dataType.getDataSupplier();
        Collector<Object, ?, Collection<Object>> collectorSupplier = collectionType.getCollector();

        long start = System.nanoTime();
        long remaining = this.numberOfObjects;
        long part = 0;

        while (remaining > 0) {
            long currentChunkSize = (long) Math.min(remaining, 20_000_000L);

            Collection<Object> chunk = Stream
                    .generate(dataSupplier)
                    .limit(currentChunkSize)
                    .collect(collectorSupplier);
            System.out.println("Processed part " + (++part) + "/" +
                    (this.getNumberOfObjects() / 20_000_000L +
                            (this.getNumberOfObjects() % 20_000_000L == 0 ? 0 : 1)) +
                    " (" + String.format(java.util.Locale.US, "%,d", currentChunkSize) + " elements)");

            chunk = null;
            System.gc();

            remaining -= currentChunkSize;
        }

        this.collection = null;
        this.testDuration = System.nanoTime() - start;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }
    public void setCollectionType(CollectionType collectionType) {
        this.collectionType = collectionType;
    }
    public void setNumberOfObjects(long numberOfObjects) {
        this.numberOfObjects = numberOfObjects;
    }

    public DataType getDataType() {
        return dataType;
    }
    public CollectionType getCollectionType() {
        return collectionType;
    }
    public long getNumberOfObjects() {
        return numberOfObjects;
    }
    public long getTestDuration() {
        return testDuration;
    }
}