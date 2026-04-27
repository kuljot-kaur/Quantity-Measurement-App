import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    // ===================== LENGTH (UC1–UC8) =====================

    QuantityMeasurementApp.LengthUnit FEET = QuantityMeasurementApp.LengthUnit.FEET;
    QuantityMeasurementApp.LengthUnit INCHES = QuantityMeasurementApp.LengthUnit.INCHES;
    QuantityMeasurementApp.LengthUnit YARDS = QuantityMeasurementApp.LengthUnit.YARDS;

    @Test
    public void testLength_1Feet_Equals_12Inches() {
        QuantityMeasurementApp.QuantityLength f =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);
        QuantityMeasurementApp.QuantityLength i =
                new QuantityMeasurementApp.QuantityLength(12.0, INCHES);

        assertTrue(f.equals(i));
    }

    @Test
    public void testLength_Addition_FeetPlusInches() {
        QuantityMeasurementApp.QuantityLength f =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);
        QuantityMeasurementApp.QuantityLength i =
                new QuantityMeasurementApp.QuantityLength(12.0, INCHES);

        QuantityMeasurementApp.QuantityLength result =
                f.add(i, FEET);

        assertEquals(2.0, result.value, 0.0001);
    }

    // ===================== WEIGHT (UC9) =====================

    QuantityMeasurementApp.WeightUnit KG = QuantityMeasurementApp.WeightUnit.KILOGRAM;
    QuantityMeasurementApp.WeightUnit GRAM = QuantityMeasurementApp.WeightUnit.GRAM;
    QuantityMeasurementApp.WeightUnit POUND = QuantityMeasurementApp.WeightUnit.POUND;

    @Test
    public void testWeight_KG_Equals_1000Gram() {
        QuantityMeasurementApp.QuantityWeight kg =
                new QuantityMeasurementApp.QuantityWeight(1.0, KG);
        QuantityMeasurementApp.QuantityWeight g =
                new QuantityMeasurementApp.QuantityWeight(1000.0, GRAM);

        assertTrue(kg.equals(g));
    }

    @Test
    public void testWeight_1KG_Equals_1KG() {
        QuantityMeasurementApp.QuantityWeight w1 =
                new QuantityMeasurementApp.QuantityWeight(1.0, KG);
        QuantityMeasurementApp.QuantityWeight w2 =
                new QuantityMeasurementApp.QuantityWeight(1.0, KG);

        assertTrue(w1.equals(w2));
    }

    @Test
    public void testWeight_Conversion_KG_To_Gram() {
        QuantityMeasurementApp.QuantityWeight kg =
                new QuantityMeasurementApp.QuantityWeight(1.0, KG);

        QuantityMeasurementApp.QuantityWeight result =
                kg.convertTo(GRAM);

        assertEquals(1000.0, result.value, 0.0001);
    }

    @Test
    public void testWeight_Conversion_Pound_To_KG() {
        QuantityMeasurementApp.QuantityWeight lb =
                new QuantityMeasurementApp.QuantityWeight(2.20462, POUND);

        QuantityMeasurementApp.QuantityWeight result =
                lb.convertTo(KG);

        assertEquals(1.0, result.value, 0.01);
    }

    // ===================== ADDITION (UC9 CORE) =====================

    @Test
    public void testWeight_Add_KGPlusGram() {
        QuantityMeasurementApp.QuantityWeight kg =
                new QuantityMeasurementApp.QuantityWeight(1.0, KG);
        QuantityMeasurementApp.QuantityWeight g =
                new QuantityMeasurementApp.QuantityWeight(1000.0, GRAM);

        QuantityMeasurementApp.QuantityWeight result =
                kg.add(g, KG);

        assertEquals(2.0, result.value, 0.0001);
    }

    @Test
    public void testWeight_Add_PoundPlusKG() {
        QuantityMeasurementApp.QuantityWeight lb =
                new QuantityMeasurementApp.QuantityWeight(2.20462, POUND);
        QuantityMeasurementApp.QuantityWeight kg =
                new QuantityMeasurementApp.QuantityWeight(1.0, KG);

        QuantityMeasurementApp.QuantityWeight result =
                lb.add(kg, POUND);

        assertEquals(4.40924, result.value, 0.01);
    }

    @Test
    public void testWeight_Add_WithZero() {
        QuantityMeasurementApp.QuantityWeight kg =
                new QuantityMeasurementApp.QuantityWeight(5.0, KG);
        QuantityMeasurementApp.QuantityWeight zero =
                new QuantityMeasurementApp.QuantityWeight(0.0, GRAM);

        QuantityMeasurementApp.QuantityWeight result =
                kg.add(zero, KG);

        assertEquals(5.0, result.value, 0.0001);
    }

    // ===================== CATEGORY SAFETY =====================

    @Test
    public void testLengthVsWeight_NotEqual() {
        QuantityMeasurementApp.QuantityLength length =
                new QuantityMeasurementApp.QuantityLength(1.0, FEET);

        QuantityMeasurementApp.QuantityWeight weight =
                new QuantityMeasurementApp.QuantityWeight(1.0, KG);

        assertFalse(length.equals(weight));
    }

    // ===================== NULL & EDGE CASES =====================

    @Test(expected = IllegalArgumentException.class)
    public void testWeight_NullUnit() {
        new QuantityMeasurementApp.QuantityWeight(1.0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWeight_NaNValue() {
        new QuantityMeasurementApp.QuantityWeight(Double.NaN, KG);
    }
}