package main;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementVolumeTest {

    private static final double DELTA = 0.0001;

    // -------- Equality Tests --------

    @Test
    public void test_LitreToLitre_SameValue() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1.0, VolumeUnit.LITRE);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void test_LitreToMillilitre_Equality() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void test_LitreToGallon_Equality() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(0.264172, VolumeUnit.GALLON);
        assertTrue(q1.equals(q2));
    }

    // -------- Conversion Tests --------

    @Test
    public void test_LitreToMillilitre_Conversion() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = q.convertTo(VolumeUnit.MILLILITRE);
        assertEquals(1000.0, result.getValue(), DELTA);
    }

    @Test
    public void test_MillilitreToLitre_Conversion() {
        Quantity<VolumeUnit> q = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = q.convertTo(VolumeUnit.LITRE);
        assertEquals(1.0, result.getValue(), DELTA);
    }

    @Test
    public void test_GallonToLitre_Conversion() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = q.convertTo(VolumeUnit.LITRE);
        assertEquals(3.78541, result.getValue(), DELTA);
    }

    // -------- Addition Tests --------

    @Test
    public void test_Add_LitreAndMillilitre_DefaultUnit() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue(), DELTA);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void test_Add_LitreAndGallon_ExplicitUnit() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result = q1.add(q2, VolumeUnit.MILLILITRE);

        assertEquals(4785.41, result.getValue(), 0.1);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void test_Add_Zeros() {
        Quantity<VolumeUnit> q1 = new Quantity<>(0.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(0.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = q1.add(q2);

        assertEquals(0.0, result.getValue(), DELTA);
    }

    // -------- Negative Test --------

    @Test(expected = IllegalArgumentException.class)
    public void test_InvalidNullUnit() {
        new Quantity<>(1.0, null);
    }
}