import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TestParameters {
    public enum DATA_TYPE{
        INTEGER( () -> new Random().nextInt() ),
        DOUBLE( () -> new Random().nextDouble() );

        private final Supplier<?> dataSupplier;
        DATA_TYPE(Supplier<?> dataSupplier) { this.dataSupplier = dataSupplier; }
        public Supplier<?> getDataSupplier() { return dataSupplier; }
    }

    public enum COLLECTION_TYPE{
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

    private TestParameters(){}

    public static TestParameters getInstance(){
        return TestParameters.instance == null? new TestParameters() : TestParameters.instance;
    }
}