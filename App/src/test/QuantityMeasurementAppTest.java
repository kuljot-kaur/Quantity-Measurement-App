package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    // ---------------- LENGTH TESTS ----------------

    @Test
    void testLengthEquality_FeetToInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(feet, inches);
    }

    @Test
    void testLengthConversion_FeetToInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = feet.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.0001);
    }

    @Test
    void testLengthAddition_SameUnit() {
        Quantity<LengthUnit> a = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(3.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = a.add(b);

        assertEquals(5.0, result.getValue(), 0.0001);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testLengthAddition_CrossUnit() {
        Quantity<LengthUnit> feet = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = feet.add(inches, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.0001);
    }

    // ---------------- WEIGHT TESTS ----------------

    @Test
    void testWeightEquality_KgToGram() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(kg, gram);
    }

    @Test
    void testWeightConversion_KgToGram() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 0.0001);
    }

    @Test
    void testWeightAddition_SameUnit() {
        Quantity<WeightUnit> a = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(3.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = a.add(b);

        assertEquals(5.0, result.getValue(), 0.0001);
    }

    @Test
    void testWeightAddition_CrossUnit() {
        Quantity<WeightUnit> kg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> gram = new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = kg.add(gram, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), 0.0001);
    }

    // ---------------- EDGE CASES ----------------

    @Test
    void testZeroValueConversion() {
        Quantity<LengthUnit> q = new Quantity<>(0.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);

        assertEquals(0.0, result.getValue(), 0.0001);
    }

    @Test
    void testNegativeAddition() {
        Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(-2.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = a.add(b);

        assertEquals(3.0, result.getValue(), 0.0001);
    }

    // ---------------- TYPE SAFETY ----------------

    @Test
    void testCrossCategoryEqualityShouldFail() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    // ---------------- NULL VALIDATION ----------------

    @Test
    void testNullUnitShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(1.0, null);
        });
    }

    @Test
    void testNaNValueShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quantity<>(Double.NaN, LengthUnit.FEET);
        });
    }
}