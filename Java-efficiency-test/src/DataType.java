import java.util.Random;
import java.util.function.Supplier;

public enum DataType {
    INTEGER(() -> new Random().nextInt()),
    DOUBLE(() -> new Random().nextDouble());

    private final Supplier<?> dataSupplier;

    DataType(Supplier<?> dataSupplier) {
        this.dataSupplier = dataSupplier;
    }

    public Supplier<?> getDataSupplier() {
        return dataSupplier;
    }
}
