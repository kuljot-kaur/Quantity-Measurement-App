import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    QuantityMeasurementApp.LengthUnit FEET = QuantityMeasurementApp.LengthUnit.FEET;
    QuantityMeasurementApp.LengthUnit INCHES = QuantityMeasurementApp.LengthUnit.INCHES;
    QuantityMeasurementApp.LengthUnit YARDS = QuantityMeasurementApp.LengthUnit.YARDS;
    QuantityMeasurementApp.LengthUnit CM = QuantityMeasurementApp.LengthUnit.CENTIMETERS;

    // ===== UC1 / UC2 / UC3 COMPATIBILITY =====

    @Test
    public void testEquality_SameFeetValue() {
        QuantityMeasurementApp.QuantityLength f1 =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);
        QuantityMeasurementApp.QuantityLength f2 =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);

        assertTrue(f1.equals(f2));
    }

    @Test
    public void testEquality_FeetAndInches_Equivalent() {
        QuantityMeasurementApp.QuantityLength f =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);
        QuantityMeasurementApp.QuantityLength i =
                new QuantityMeasurementApp.QuantityLength(12.0, INCHES);

        assertTrue(f.equals(i));
    }

    @Test
    public void testEquality_DifferentValues() {
        QuantityMeasurementApp.QuantityLength f1 =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);
        QuantityMeasurementApp.QuantityLength f2 =
                new QuantityMeasurementApp.QuantityLength(2.0, FEET);

        assertFalse(f1.equals(f2));
    }

    @Test
    public void testEquality_NullComparison() {
        QuantityMeasurementApp.QuantityLength f =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);

        assertFalse(f.equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        QuantityMeasurementApp.QuantityLength f =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);

        assertTrue(f.equals(f));
    }

    // ===== UC5 CONVERSION =====

    @Test
    public void testConvert_FeetToInches() {
        QuantityMeasurementApp.QuantityLength f =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);

        QuantityMeasurementApp.QuantityLength result =
                f.convertTo(INCHES);

        assertEquals(12.0, result.value, 0.0001);
    }

    @Test
    public void testConvert_YardsToFeet() {
        QuantityMeasurementApp.QuantityLength y =
                new QuantityMeasurementApp.QuantityLength(1.0, YARDS);

        QuantityMeasurementApp.QuantityLength result =
                y.convertTo(FEET);

        assertEquals(3.0, result.value, 0.0001);
    }

    // ===== UC6 ADDITION =====

    @Test
    public void testAddition_FeetPlusFeet() {
        QuantityMeasurementApp.QuantityLength f1 =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);
        QuantityMeasurementApp.QuantityLength f2 =
                new QuantityMeasurementApp.QuantityLength(2.0, FEET);

        QuantityMeasurementApp.QuantityLength result =
                f1.add(f2, FEET);

        assertEquals(3.0, result.value, 0.0001);
    }

    @Test
    public void testAddition_FeetPlusInches() {
        QuantityMeasurementApp.QuantityLength f =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);
        QuantityMeasurementApp.QuantityLength i =
                new QuantityMeasurementApp.QuantityLength(12.0, INCHES);

        QuantityMeasurementApp.QuantityLength result =
                f.add(i, FEET);

        assertEquals(2.0, result.value, 0.0001);
    }

    @Test
    public void testAddition_Commutative() {
        QuantityMeasurementApp.QuantityLength f =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);
        QuantityMeasurementApp.QuantityLength i =
                new QuantityMeasurementApp.QuantityLength(12.0, INCHES);

        QuantityMeasurementApp.QuantityLength r1 = f.add(i, FEET);
        QuantityMeasurementApp.QuantityLength r2 = i.add(f, FEET);

        assertEquals(r1.value, r2.value, 0.0001);
    }

    // ===== UC7 TARGET UNIT =====

    @Test
    public void testAddition_TargetUnit_Yards() {
        QuantityMeasurementApp.QuantityLength f =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);
        QuantityMeasurementApp.QuantityLength i =
                new QuantityMeasurementApp.QuantityLength(12.0, INCHES);

        QuantityMeasurementApp.QuantityLength result =
                f.add(i, YARDS);

        assertEquals(0.6667, result.value, 0.01);
    }

    // ===== EDGE CASES =====

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidValue_NaN() {
        new QuantityMeasurementApp.QuantityLength(Double.NaN, FEET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidUnit_Null() {
        new QuantityMeasurementApp.QuantityLength(1.0, null);
    }
}