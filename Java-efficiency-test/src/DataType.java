import java.util.Random;
import java.util.function.Supplier;

public enum DataType {
    INTEGER("Integer",() -> new Random().nextInt()),
    DOUBLE("Double",() -> new Random().nextDouble()),
    PERSON("Person",Person::new),
    COLOR("Color", Color::new);

    private final Supplier<?> dataSupplier;
    private final String label;

    DataType(String label,Supplier<?> dataSupplier) {
        this.label = label;
        this.dataSupplier = dataSupplier;
    }

    public Supplier<?> getDataSupplier() {
        return dataSupplier;
    }

    @Override
    public String toString() {
        return label;
    }
}
