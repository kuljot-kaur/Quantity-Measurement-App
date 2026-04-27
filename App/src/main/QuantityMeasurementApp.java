package main;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(12.0),
        INCH(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double factor;

        LengthUnit(double factor) {
            this.factor = factor;
        }

        public double getFactor() {
            return factor;
        }
    }

    // ================= CORE VALUE CLASS =================

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toInches() {
            return value * unit.getFactor();
        }

        public double convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }
            double baseInches = this.toInches();
            return baseInches / targetUnit.getFactor();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;

            QuantityLength that = (QuantityLength) obj;

            return Math.abs(this.toInches() - that.toInches()) < 0.0001;
        }
    }

    // ================= STATIC API =================

    public static double convert(double value, LengthUnit from, LengthUnit to) {
        if (!Double.isFinite(value) || from == null || to == null) {
            throw new IllegalArgumentException("Invalid input for conversion");
        }

        double baseInches = value * from.getFactor();
        return baseInches / to.getFactor();
    }
}