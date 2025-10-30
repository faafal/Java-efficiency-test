import java.util.Random;
import java.util.function.Supplier;

public class TestParameters {
    public enum DATA_TYPE{
        INTEGER( () -> new Random().nextInt() ),
        DOUBLE( () -> new Random().nextDouble() );

        private final Supplier<?> dataSupplier;
        DATA_TYPE(Supplier<?> dataSupplier) { this.dataSupplier = dataSupplier; }
        public Supplier<?> getDataSupplier() { return dataSupplier; }
    }

    private static TestParameters instance;

    private TestParameters(){}

    public static TestParameters getInstance(){
        return TestParameters.instance == null? new TestParameters() : TestParameters.instance;
    }
}