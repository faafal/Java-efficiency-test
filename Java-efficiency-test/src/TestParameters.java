import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestParameters {
    public enum DATA_TYPE {
        INTEGER(() -> new Random().nextInt()),
        DOUBLE(() -> new Random().nextDouble());

        private final Supplier<?> dataSupplier;

        DATA_TYPE(Supplier<?> dataSupplier) {
            this.dataSupplier = dataSupplier;
        }

        public Supplier<?> getDataSupplier() {
            return dataSupplier;
        }
    }

    public enum COLLECTION_TYPE {
        ARRAY_LIST(() -> Collectors.toCollection(ArrayList::new)),
        LINKED_LIST(() -> Collectors.toCollection(LinkedList::new)),
        HASH_SET(() -> Collectors.toCollection(HashSet::new)),
        TREE_SET(() -> Collectors.toCollection(TreeSet::new));

        private final Supplier<Collector<Object, ?, Collection<Object>>> collectorSupplier;

        COLLECTION_TYPE(Supplier<Collector<Object, ?, Collection<Object>>> collectorSupplier) {
            this.collectorSupplier = collectorSupplier;
        }

        public Collector<Object, ?, Collection<Object>> getCollector() {
            return collectorSupplier.get();
        }
    }

    private static TestParameters instance;
    private DATA_TYPE dataType;
    private COLLECTION_TYPE collectionType;
    private int numberOfObjects;

    private TestParameters() {
        this.collectionType = COLLECTION_TYPE.ARRAY_LIST;
        this.dataType = DATA_TYPE.INTEGER;
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

    public void setDataType(DATA_TYPE dataType) {
        this.dataType = dataType;
    }
    public void setCollectionType(COLLECTION_TYPE collectionType) {
        this.collectionType = collectionType;
    }
    public void setNumberOfObjects(int numberOfObjects) {
        this.numberOfObjects = numberOfObjects;
    }

    public DATA_TYPE getDataType() {
        return dataType;
    }
    public COLLECTION_TYPE getCollectionType() {
        return collectionType;
    }
    public int getNumberOfObjects() {
        return numberOfObjects;
    }
}