package test;

import main.QuantityMeasurementApp;
import main.QuantityMeasurementApp.LengthUnit;
import main.QuantityMeasurementApp.QuantityLength;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ================= BASIC CONVERSIONS =================

    @Test
    void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCH),
                0.0001);
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(24.0, LengthUnit.INCH, LengthUnit.FEET),
                0.0001);
    }

    @Test
    void testConversion_YardsToFeet() {
        assertEquals(3.0,
                QuantityMeasurementApp.convert(1.0, LengthUnit.YARDS, LengthUnit.FEET),
                0.0001);
    }

    @Test
    void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityMeasurementApp.convert(1.0, LengthUnit.YARDS, LengthUnit.INCH),
                0.0001);
    }

    // ================= CENTIMETERS =================

    @Test
    void testConversion_CmToInches() {
        assertEquals(1.0,
                QuantityMeasurementApp.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCH),
                0.01);
    }

    @Test
    void testConversion_CmToFeet() {
        double result = QuantityMeasurementApp.convert(30.48, LengthUnit.CENTIMETERS, LengthUnit.FEET);
        assertEquals(1.0, result, 0.01);
    }

    // ================= EDGE CASES =================

    @Test
    void testConversion_ZeroValue() {
        assertEquals(0.0,
                QuantityMeasurementApp.convert(0.0, LengthUnit.FEET, LengthUnit.INCH),
                0.0001);
    }

    @Test
    void testConversion_NegativeValue() {
        assertEquals(-12.0,
                QuantityMeasurementApp.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH),
                0.0001);
    }

    // ================= ROUND TRIP =================

    @Test
    void testConversion_RoundTrip() {
        double converted = QuantityMeasurementApp.convert(1.0, LengthUnit.FEET, LengthUnit.INCH);
        double back = QuantityMeasurementApp.convert(converted, LengthUnit.INCH, LengthUnit.FEET);

        assertEquals(1.0, back, 0.0001);
    }

    // ================= INVALID INPUT =================

    @Test
    void testConversion_InvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(1.0, null, LengthUnit.FEET);
        });
    }

    @Test
    void testConversion_NaNValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityMeasurementApp.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH);
        });
    }

    // ================= OBJECT EQUALITY =================

    @Test
    void testEquality_SameUnit() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_CrossUnit() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);

        assertTrue(feet.equals(inch));
    }

    @Test
    void testEquality_NotEqual() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

        assertFalse(q1.equals(q2));
    }
}