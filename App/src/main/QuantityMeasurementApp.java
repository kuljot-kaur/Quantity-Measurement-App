package main;
public class QuantityMeasurementApp {

    // ===== ENUM (Standalone Unit with Conversion Responsibility) =====
    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double conversionFactorToFeet;

        LengthUnit(double conversionFactorToFeet) {
            this.conversionFactorToFeet = conversionFactorToFeet;
        }

        public double toBaseUnit(double value) {
            return value * conversionFactorToFeet; // convert to feet
        }

        public double fromBaseUnit(double baseValue) {
            return baseValue / conversionFactorToFeet; // from feet
        }
    }

    // ===== VALUE OBJECT CLASS =====
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
                throw new IllegalArgumentException("Invalid input");
            }
            this.value = value;
            this.unit = unit;
        }

        // Convert this to base unit (feet)
        private double toBase() {
            return unit.toBaseUnit(value);
        }

        // Equality
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        // Convert to another unit
        public QuantityLength convertTo(LengthUnit target) {
            double base = this.toBase();
            return new QuantityLength(target.fromBaseUnit(base), target);
        }

        // UC6 + UC7 ADDITION (target unit flexible)
        public QuantityLength add(QuantityLength other, LengthUnit target) {
            if (other == null || target == null) {
                throw new IllegalArgumentException("Null not allowed");
            }

            double sumInFeet = this.toBase() + other.toBase();
            double result = target.fromBaseUnit(sumInFeet);

            return new QuantityLength(result, target);
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // ===== MAIN METHOD =====
    public static void main(String[] args) {

        QuantityLength f1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength f2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println("Equality:");
        System.out.println(f1.equals(f2)); // true

        System.out.println("\nConversion:");
        System.out.println(f1.convertTo(LengthUnit.INCHES)); // 12 inches

        System.out.println("\nAddition:");
        System.out.println(f1.add(f2, LengthUnit.FEET)); // 2 feet

        System.out.println("\nAddition in YARDS:");
        System.out.println(f1.add(f2, LengthUnit.YARDS));
    }
}