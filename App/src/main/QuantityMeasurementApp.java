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

    // ================= VALUE OBJECT =================

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid value");
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

        // ================= ADDITION LOGIC =================

        public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {

            if (q1 == null || q2 == null || targetUnit == null) {
                throw new IllegalArgumentException("Null values not allowed");
            }

            double q1Inches = q1.toInches();
            double q2Inches = q2.toInches();

            double sumInches = q1Inches + q2Inches;

            double resultValue = sumInches / targetUnit.getFactor();

            return new QuantityLength(resultValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;

            QuantityLength that = (QuantityLength) obj;

            return Math.abs(this.toInches() - that.toInches()) < 0.0001;
        }
    }
}