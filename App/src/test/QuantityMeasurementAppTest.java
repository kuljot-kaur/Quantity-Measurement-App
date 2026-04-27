package test;

import main.QuantityMeasurementApp;
import main.QuantityMeasurementApp.LengthUnit;
import main.QuantityMeasurementApp.QuantityLength;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ================= YARD TESTS =================

    @Test
    void testEquality_YardToYard_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.YARDS);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.YARDS);

        assertFalse(q1.equals(q2));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);

        assertTrue(feet.equals(yard));
    }

    @Test
    void testEquality_YardToInch_EquivalentValue() {
        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength inch = new QuantityLength(36.0, LengthUnit.INCH);

        assertTrue(yard.equals(inch));
    }

    // ================= CENTIMETER TESTS =================

    @Test
    void testEquality_CmToCm_SameValue() {
        QuantityLength q1 = new QuantityLength(2.0, LengthUnit.CENTIMETERS);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.CENTIMETERS);

        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_CmToInch_EquivalentValue() {
        QuantityLength cm = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength inch = new QuantityLength(0.393701, LengthUnit.INCH);

        assertTrue(cm.equals(inch));
    }

    @Test
    void testEquality_CmToFeet_NonEquivalent() {
        QuantityLength cm = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(cm.equals(feet));
    }

    // ================= EDGE CASES =================

    @Test
    void testEquality_SameReference() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);

        assertTrue(q1.equals(q1));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);

        assertFalse(q1.equals(null));
    }

    // ================= TRANSITIVE TEST =================

    @Test
    void testEquality_MultiUnit_Transitive() {
        QuantityLength yard = new QuantityLength(2.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(6.0, LengthUnit.FEET);
        QuantityLength inch = new QuantityLength(72.0, LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }
}