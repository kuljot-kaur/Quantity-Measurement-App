package main;

public class QuantityMeasurementApp {

    // ================= UNIT ENUM =================
    public enum LengthUnit {
        FEET(12.0),
        INCH(1.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // ================= GENERIC QUANTITY CLASS =================
    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        // Convert everything to INCH as base unit
        private double toBaseUnit() {
            return this.value * this.unit.getConversionFactor();
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Input: 1.0 feet and 12.0 inch");
        System.out.println("Output: " + q1.equals(q2));
    }
}