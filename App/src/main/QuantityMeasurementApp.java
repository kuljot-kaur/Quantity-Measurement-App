package main;

import java.util.Objects;

public class QuantityMeasurementApp {

    // ===================== MAIN =====================
    public static void main(String[] args) {

        // ===== UC11: VOLUME =====
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> v3 = new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("=== VOLUME OPERATIONS ===");

        // Equality
        System.out.println("Equality Litre vs Millilitre: " + v1.equals(v2));

        // Conversion
        System.out.println("Convert Litre to mL: " + v1.convertTo(VolumeUnit.MILLILITRE));

        // Addition
        System.out.println("Addition: " + v1.add(v2));

        // Subtraction
        System.out.println("Subtraction: " +
                v1.subtract(new Quantity<>(500.0, VolumeUnit.MILLILITRE)));

        // Division
        System.out.println("Division: " + v1.divide(v2));

        System.out.println("\n=== UC12 OPERATIONS ===");

        Quantity<VolumeUnit> a = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(2.0, VolumeUnit.LITRE);

        System.out.println("Subtraction: " + a.subtract(b));
        System.out.println("Subtraction (ML): " + a.subtract(b, VolumeUnit.MILLILITRE));
        System.out.println("Division: " + a.divide(b));
    }
}

// ===================== IMEASURABLE =====================
interface IMeasurable {
    double getConversionFactor();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();
}

// ===================== VOLUME UNIT =====================
enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() {
        return name();
    }
}

// ===================== GENERIC QUANTITY CLASS =====================
class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (Double.isNaN(value) || Double.isInfinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    // ---------- GETTERS ----------
    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ---------- BASE CONVERSION ----------
    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    private double fromBase(double base, U target) {
        return target.convertFromBaseUnit(base);
    }

    // ---------- EQUALITY ----------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;

        if (!unit.getClass().equals(other.unit.getClass())) return false;

        return Math.abs(this.toBase() - other.toBase()) < 0.0001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(), toBase());
    }

    // ---------- CONVERSION ----------
    public Quantity<U> convertTo(U target) {
        double base = toBase();
        return new Quantity<>(target.convertFromBaseUnit(base), target);
    }

    // ---------- ADDITION ----------
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U target) {
        double result = toBase() + other.toBase();
        return new Quantity<>(target.convertFromBaseUnit(result), target);
    }

    // ---------- SUBTRACTION ----------
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U target) {
        validate(other);

        double result = toBase() - other.toBase();
        return new Quantity<>(target.convertFromBaseUnit(result), target);
    }

    // ---------- DIVISION ----------
    public double divide(Quantity<U> other) {
        validate(other);

        double divisor = other.toBase();
        if (divisor == 0) throw new ArithmeticException("Division by zero");

        return toBase() / divisor;
    }

    // ---------- VALIDATION ----------
    private void validate(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Null quantity");

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cross-category operation not allowed");
        }
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}