import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public enum CollectionType {
    ARRAY_LIST(() -> Collectors.toCollection(ArrayList::new)),
    LINKED_LIST(() -> Collectors.toCollection(LinkedList::new)),
    HASH_SET(() -> Collectors.toCollection(HashSet::new)),
    TREE_SET(() -> Collectors.toCollection(TreeSet::new));

    private final Supplier<Collector<Object, ?, Collection<Object>>> collectorSupplier;

    CollectionType(Supplier<Collector<Object, ?, Collection<Object>>> collectorSupplier) {
        this.collectorSupplier = collectorSupplier;
    }

    public Collector<Object, ?, Collection<Object>> getCollector() {
        return collectorSupplier.get();
    }
}
