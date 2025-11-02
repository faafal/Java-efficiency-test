import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public enum CollectionType {
    ARRAY_LIST("Array list", () -> Collectors.toCollection(ArrayList::new)),
    LINKED_LIST("Linked list", () -> Collectors.toCollection(LinkedList::new)),
    HASH_SET("Hash set", () -> Collectors.toCollection(HashSet::new)),
    TREE_SET("Tree set", () -> Collectors.toCollection(TreeSet::new));

    private final Supplier<Collector<Object, ?, Collection<Object>>> collectorSupplier;
    private final String label;

    CollectionType(String label, Supplier<Collector<Object, ?, Collection<Object>>> collectorSupplier) {
        this.label = label;
        this.collectorSupplier = collectorSupplier;
    }

    public Collector<Object, ?, Collection<Object>> getCollector() {
        return collectorSupplier.get();
    }
    @Override
    public String toString() {
        return label;
    }
}
