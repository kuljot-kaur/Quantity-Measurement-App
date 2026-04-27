package main;

enum LengthUnit {
    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}

class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit null");
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    private double toFeet() {
        return value * unit.getFactor();
    }

    public static double convert(double value, LengthUnit from, LengthUnit to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Unit null");
        }

        double feet = value * from.getFactor();
        return feet / to.getFactor();
    }

    public QuantityLength add(QuantityLength other, LengthUnit target) {
        double sum = this.toFeet() + other.toFeet();
        return new QuantityLength(sum / target.getFactor(), target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;
        return Double.compare(this.toFeet(), other.toFeet()) == 0;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}

public class QuantityMeasurementApp {

    public static double convert(double value, LengthUnit from, LengthUnit to) {
        return QuantityLength.convert(value, from, to);
    }

    public static QuantityLength add(double v1, LengthUnit u1,
                                     double v2, LengthUnit u2,
                                     LengthUnit target) {
        return new QuantityLength(v1, u1)
                .add(new QuantityLength(v2, u2), target);
    }

    public static void main(String[] args) {

        System.out.println(convert(1.0, LengthUnit.FEET, LengthUnit.INCH));
        System.out.println(convert(3.0, LengthUnit.YARD, LengthUnit.FEET));

        System.out.println(add(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH, LengthUnit.FEET));
    }
}