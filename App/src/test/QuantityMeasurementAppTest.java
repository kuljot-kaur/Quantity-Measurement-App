package test;

import main.QuantityMeasurementApp;
import main.QuantityMeasurementApp.LengthUnit;
import main.QuantityMeasurementApp.QuantityLength;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ================= SAME UNIT =================

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.FEET);

        assertEquals(3.0, result.convertTo(LengthUnit.FEET), 0.0001);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        QuantityLength q1 = new QuantityLength(6.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(6.0, LengthUnit.INCH);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.INCH);

        assertEquals(12.0, result.convertTo(LengthUnit.INCH), 0.0001);
    }

    // ================= CROSS UNIT =================

    @Test
    void testAddition_CrossUnit_FeetPlusInch() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.FEET);

        assertEquals(2.0, result.convertTo(LengthUnit.FEET), 0.0001);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        QuantityLength q1 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.INCH);

        assertEquals(24.0, result.convertTo(LengthUnit.INCH), 0.0001);
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.YARDS);

        assertEquals(2.0, result.convertTo(LengthUnit.YARDS), 0.0001);
    }

    // ================= CENTIMETERS =================

    @Test
    void testAddition_CmPlusInch() {
        QuantityLength q1 = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.convertTo(LengthUnit.CENTIMETERS), 0.01);
    }

    // ================= EDGE CASES =================

    @Test
    void testAddition_WithZero() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(0.0, LengthUnit.INCH);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.FEET);

        assertEquals(5.0, result.convertTo(LengthUnit.FEET), 0.0001);
    }

    @Test
    void testAddition_NegativeValues() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result = QuantityLength.add(q1, q2, LengthUnit.FEET);

        assertEquals(3.0, result.convertTo(LengthUnit.FEET), 0.0001);
    }

    // ================= COMMUTATIVITY =================

    @Test
    void testAddition_Commutativity() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength r1 = QuantityLength.add(q1, q2, LengthUnit.FEET);
        QuantityLength r2 = QuantityLength.add(q2, q1, LengthUnit.FEET);

        assertEquals(r1.convertTo(LengthUnit.FEET), r2.convertTo(LengthUnit.FEET), 0.0001);
    }

    // ================= INVALID INPUT =================

    @Test
    void testAddition_NullOperand() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.add(q1, null, LengthUnit.FEET)
        );
    }
}