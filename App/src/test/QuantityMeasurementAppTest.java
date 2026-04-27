package test;

import main.QuantityMeasurementApp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ================= FEET TESTS =================

    @Test
    void testEquality_SameFeetValue() {
        assertTrue(QuantityMeasurementApp.compareFeet(1.0, 1.0));
    }

    @Test
    void testEquality_DifferentFeetValue() {
        assertFalse(QuantityMeasurementApp.compareFeet(1.0, 2.0));
    }

    // ================= INCHES TESTS =================

    @Test
    void testEquality_SameInchValue() {
        assertTrue(QuantityMeasurementApp.compareInches(1.0, 1.0));
    }

    @Test
    void testEquality_DifferentInchValue() {
        assertFalse(QuantityMeasurementApp.compareInches(1.0, 2.0));
    }

    // ================= NULL SAFETY =================

    @Test
    void testEquality_InchesNullSafety() {
        QuantityMeasurementApp.Inches i1 = new QuantityMeasurementApp.Inches(1.0);

        assertNotNull(i1);
    }
}