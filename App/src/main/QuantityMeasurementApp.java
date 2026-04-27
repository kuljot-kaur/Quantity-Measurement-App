package main;

public class QuantityMeasurementApp {

    public enum LengthUnit {
        FEET(12.0),
        INCH(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double unitValueInInch;

        LengthUnit(double unitValueInInch) {
            this.unitValueInInch = unitValueInInch;
        }

        public double getUnitValueInInch() {
            return unitValueInInch;
        }
    }

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double convertToInches() {
            return this.value * unit.getUnitValueInInch();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength that = (QuantityLength) obj;

            return Double.compare(this.convertToInches(), that.convertToInches()) == 0;
        }
    }
}