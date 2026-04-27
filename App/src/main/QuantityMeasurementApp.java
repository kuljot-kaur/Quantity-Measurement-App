package main;

import java.util.Objects;

// -------------------- IMeasurable --------------------
interface IMeasurable {
    double getFactor();
    double toBase(double value);      // to base unit
    double fromBase(double baseValue); // from base unit
}

// -------------------- LengthUnit --------------------
enum LengthUnit implements IMeasurable {
    FEET(1.0),
    INCHES(1.0 / 12),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }

    public double toBase(double value) {
        return value * factor;
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}

// -------------------- WeightUnit --------------------
enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }

    public double toBase(double value) {
        return value * factor;
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}

// -------------------- Generic Quantity --------------------
class Quantity<U extends Enum<U> & IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // -------- Convert --------
    public Quantity<U> convertTo(U targetUnit) {
        double base = unit.toBase(value);
        double converted = targetUnit.fromBase(base);
        return new Quantity<>(converted, targetUnit);
    }

    // -------- Equality --------
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quantity<?> other = (Quantity<?>) obj;

        double thisBase = this.unit.toBase(this.value);
        double otherBase = other.unit.toBase(other.value);

        return Double.compare(thisBase, otherBase) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit);
    }

    // -------- Addition (default unit = first operand) --------
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    // -------- Addition (explicit target unit) --------
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double sumBase = this.unit.toBase(this.value)
                + other.unit.toBase(other.value);

        double result = targetUnit.fromBase(sumBase);
        return new Quantity<>(result, targetUnit);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}

// -------------------- MAIN APP --------------------
public class Main {

    public static void main(String[] args) {

        // -------- LENGTH --------
        Quantity<LengthUnit> len1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> len2 = new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println(len1.equals(len2)); // true
        System.out.println(len1.convertTo(LengthUnit.INCHES));
        System.out.println(len1.add(len2, LengthUnit.FEET));

        // -------- WEIGHT --------
        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println(w1.equals(w2)); // true
        System.out.println(w1.convertTo(WeightUnit.GRAM));
        System.out.println(w1.add(w2, WeightUnit.KILOGRAM));

        // -------- CROSS CATEGORY SAFE (compile-time blocked) --------
        // Quantity<LengthUnit> wrong = new Quantity<>(1.0, WeightUnit.KILOGRAM); ❌ not allowed
    }
}