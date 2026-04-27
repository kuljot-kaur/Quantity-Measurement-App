package main;

import java.util.Objects;

// ===================== MAIN CLASS =====================
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        System.out.println("=== UC13 DEMO ===");

        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> q3 = new Quantity<>(2.0, VolumeUnit.LITRE);

        // ADDITION
        System.out.println("Add: " + q1.add(q2));

        // SUBTRACTION
        System.out.println("Subtract: " + q3.subtract(q1));

        // SUBTRACTION with target unit
        System.out.println("Subtract (ML): " + q3.subtract(q1, VolumeUnit.MILLILITRE));

        // DIVISION
        System.out.println("Divide: " + q3.divide(q1));
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

// ===================== ARITHMETIC OPERATION ENUM (UC13 CORE) =====================
enum ArithmeticOperation {

    ADD {
        public double compute(double a, double b) {
            return a + b;
        }
    },

    SUBTRACT {
        public double compute(double a, double b) {
            return a - b;
        }
    },

    DIVIDE {
        public double compute(double a, double b) {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        }
    };

    public abstract double compute(double a, double b);
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

    // ===================== BASE CONVERSION =====================
    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    private double fromBase(double baseValue, U target) {
        return target.convertFromBaseUnit(baseValue);
    }

    // ===================== CENTRALIZED VALIDATION (UC13 CORE) =====================
    private void validate(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Quantity cannot be null");

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cross-category operation not allowed");
        }

        if (Double.isNaN(other.value) || Double.isInfinite(other.value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
    }

    // ===================== CENTRALIZED ARITHMETIC (DRY CORE) =====================
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation op) {
        validate(other);
        return op.compute(this.toBase(), other.toBase());
    }

    // ===================== ADDITION =====================
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U target) {
        double result = performBaseArithmetic(other, ArithmeticOperation.ADD);
        return new Quantity<>(target.convertFromBaseUnit(result), target);
    }

    // ===================== SUBTRACTION =====================
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U target) {
        double result = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(target.convertFromBaseUnit(result), target);
    }

    // ===================== DIVISION =====================
    public double divide(Quantity<U> other) {
        double result = performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
        return result;
    }

    // ===================== EQUALITY =====================
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

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}