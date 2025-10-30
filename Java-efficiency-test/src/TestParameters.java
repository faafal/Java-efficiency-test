import java.util.Random;
import java.util.function.Supplier;

public class TestParameters {
    private static TestParameters instance;

    private TestParameters(){}

    public static TestParameters getInstance(){
        return TestParameters.instance == null? new TestParameters() : TestParameters.instance;
    }
}