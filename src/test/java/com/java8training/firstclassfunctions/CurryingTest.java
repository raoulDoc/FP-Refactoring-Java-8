package com.java8training.firstclassfunctions;

        import org.junit.Test;

        import java.util.function.DoubleUnaryOperator;

        import static org.junit.Assert.assertEquals;

public class CurryingTest {

    // (double, double, double) -> double
    static double convert(double amount, double factor, double base) {
        return amount * factor + base;
    }

    // (double, double) -> (double -> double)
    static DoubleUnaryOperator convert(double factor, double base) {
        return amount -> amount * factor + base;
    }

    @Test
    public void celsiusShouldConvertToFahrenheit() {
        double result = convert(10, 1.8, 32);
        assertEquals(result, 50, 0.0);

        result = convert(-40, 1.8, 32);
        assertEquals(result, -40, 0.0);
    }

    @Test
    public void celsiusShouldConvertToFahrenheitCurried() {
        DoubleUnaryOperator convertCtoF = convert(1.8, 32);

        DoubleUnaryOperator convert$to£ = convert(0.6, 0);

        DoubleUnaryOperator convertKmToMi = convert(0.62137, 0);

        double result = convertCtoF.applyAsDouble(10);
        assertEquals(result, 50, 0.0);

        result = convertCtoF.applyAsDouble(-40);
        assertEquals(result, -40, 0.0);
    }

}
