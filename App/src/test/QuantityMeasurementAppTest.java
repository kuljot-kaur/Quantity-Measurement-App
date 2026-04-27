import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementUC12Test {

    // -------------------- SUBTRACTION TESTS --------------------

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(6.0, LengthUnit.INCHES));

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_ResultingInNegative() {
        Quantity<LengthUnit> result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_ResultingInZero() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(120.0, LengthUnit.INCHES));

        assertEquals(new Quantity<>(0.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_WithZeroOperand() {
        Quantity<LengthUnit> result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(0.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_NonCommutative() {
        Quantity<LengthUnit> a =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        Quantity<LengthUnit> b =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(10.0, LengthUnit.FEET));

        assertNotEquals(a, b);
    }

    @Test
    void testSubtraction_CrossCategory() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(10.0, LengthUnit.FEET)
                    .subtract(new Quantity<>(5.0, WeightUnit.KILOGRAM));
        });
    }

    // -------------------- DIVISION TESTS --------------------

    @Test
    void testDivision_SameUnit() {
        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(5.0, result);
    }

    @Test
    void testDivision_RatioLessThanOne() {
        double result =
                new Quantity<>(5.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(0.5, result);
    }

    @Test
    void testDivision_RatioEqualToOne() {
        double result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(10.0, LengthUnit.FEET));

        assertEquals(1.0, result);
    }

    @Test
    void testDivision_ByZero() {
        assertThrows(ArithmeticException.class, () -> {
            new Quantity<>(10.0, LengthUnit.FEET)
                    .divide(new Quantity<>(0.0, LengthUnit.FEET));
        });
    }

    @Test
    void testDivision_CrossUnit() {
        double result =
                new Quantity<>(24.0, LengthUnit.INCHES)
                        .divide(new Quantity<>(2.0, LengthUnit.FEET));

        assertEquals(1.0, result);
    }

    @Test
    void testDivision_CrossCategory() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(10.0, LengthUnit.FEET)
                    .divide(new Quantity<>(5.0, WeightUnit.KILOGRAM));
        });
    }

    // -------------------- INTEGRATION TESTS --------------------

    @Test
    void testSubtractionAndDivision_Integration() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .subtract(new Quantity<>(2.0, LengthUnit.FEET));

        double division = result.divide(new Quantity<>(4.0, LengthUnit.FEET));

        assertEquals(2.0, division);
    }

    @Test
    void testSubtractionAddition_Inverse() {
        Quantity<LengthUnit> result =
                new Quantity<>(10.0, LengthUnit.FEET)
                        .add(new Quantity<>(5.0, LengthUnit.FEET))
                        .subtract(new Quantity<>(5.0, LengthUnit.FEET));

        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), result);
    }

    @Test
    void testSubtraction_Immutability() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        q1.subtract(q2);

        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), q1);
    }

    @Test
    void testDivision_Immutability() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        q1.divide(q2);

        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), q1);
    }
}