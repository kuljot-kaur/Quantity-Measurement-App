public class QuantityMeasurementApp {

    // =========================================================
    // 🔷 LENGTH UNIT (UC1–UC8)
    // =========================================================
    public enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12),
        YARDS(3.0),
        CENTIMETERS(1.0 / 30.48);

        private final double factorToFeet;

        LengthUnit(double factorToFeet) {
            this.factorToFeet = factorToFeet;
        }

        public double toBase(double value) {
            return value * factorToFeet;
        }

        public double fromBase(double baseValue) {
            return baseValue / factorToFeet;
        }
    }

    // =========================================================
    // 🔷 WEIGHT UNIT (UC9)
    // =========================================================
    public enum WeightUnit {
        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double factorToKg;

        WeightUnit(double factorToKg) {
            this.factorToKg = factorToKg;
        }

        public double toBase(double value) {
            return value * factorToKg; // kg
        }

        public double fromBase(double baseValue) {
            return baseValue / factorToKg;
        }
    }

    // =========================================================
    // 🔷 LENGTH CLASS
    // =========================================================
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null || Double.isNaN(value) || Double.isInfinite(value))
                throw new IllegalArgumentException("Invalid input");

            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.toBase(value);
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;

            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        public QuantityLength convertTo(LengthUnit target) {
            double base = this.toBase();
            return new QuantityLength(target.fromBase(base), target);
        }

        public QuantityLength add(QuantityLength other, LengthUnit target) {
            double sum = this.toBase() + other.toBase();
            return new QuantityLength(target.fromBase(sum), target);
        }

        @Override
        public String toString() {
            return "QuantityLength(" + value + ", " + unit + ")";
        }
    }

    // =========================================================
    // 🔷 WEIGHT CLASS
    // =========================================================
    public static class QuantityWeight {
        private final double value;
        private final WeightUnit unit;

        public QuantityWeight(double value, WeightUnit unit) {
            if (unit == null || Double.isNaN(value) || Double.isInfinite(value))
                throw new IllegalArgumentException("Invalid input");

            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.toBase(value); // kg
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityWeight)) return false;

            QuantityWeight other = (QuantityWeight) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        public QuantityWeight convertTo(WeightUnit target) {
            double base = this.toBase();
            return new QuantityWeight(target.fromBase(base), target);
        }

        public QuantityWeight add(QuantityWeight other, WeightUnit target) {
            double sum = this.toBase() + other.toBase();
            return new QuantityWeight(target.fromBase(sum), target);
        }

        @Override
        public String toString() {
            return "QuantityWeight(" + value + ", " + unit + ")";
        }
    }

    // =========================================================
    // 🔷 MAIN METHOD (TEST RUN)
    // =========================================================
    public static void main(String[] args) {

        // -------- LENGTH TEST --------
        QuantityLength length1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength length2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println("=== LENGTH ===");
        System.out.println("Equal: " + length1.equals(length2));
        System.out.println("Convert: " + length1.convertTo(LengthUnit.INCHES));
        System.out.println("Add: " + length1.add(length2, LengthUnit.FEET));

        // -------- WEIGHT TEST --------
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println("\n=== WEIGHT ===");
        System.out.println("Equal: " + w1.equals(w2));
        System.out.println("Convert: " + w1.convertTo(WeightUnit.GRAM));
        System.out.println("Add: " + w1.add(w2, WeightUnit.KILOGRAM));

        // -------- CROSS CATEGORY SAFETY --------
        System.out.println("\n=== CATEGORY SAFETY ===");
        System.out.println("Length vs Weight: " + w1.equals(length1));
    }
}