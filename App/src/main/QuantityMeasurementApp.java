package main;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // =========================
        // LENGTH OPERATIONS
        // =========================
        System.out.println("===== LENGTH =====");

        Quantity<LengthUnit> length1 =
                new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("Equal (Feet vs Inches): " +
                length1.equals(length2));

        System.out.println("Add Length (default unit): " +
                length1.add(length2));

        System.out.println("Add Length (Yards): " +
                length1.add(length2, LengthUnit.YARDS));

        System.out.println("Convert Feet to Inches: " +
                length1.convertTo(LengthUnit.INCHES));


        // =========================
        // WEIGHT OPERATIONS
        // =========================
        System.out.println("\n===== WEIGHT =====");

        Quantity<WeightUnit> weight1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weight2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Equal (Kg vs Gram): " +
                weight1.equals(weight2));

        System.out.println("Add Weight (default unit): " +
                weight1.add(weight2));

        System.out.println("Add Weight (Gram): " +
                weight1.add(weight2, WeightUnit.GRAM));

        System.out.println("Convert Kg to Gram: " +
                weight1.convertTo(WeightUnit.GRAM));


        // =========================
        // VOLUME OPERATIONS
        // =========================
        System.out.println("\n===== VOLUME =====");

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> volume3 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("Equal (Litre vs Millilitre): " +
                volume1.equals(volume2));

        System.out.println("Equal (Litre vs Gallon): " +
                volume1.equals(volume3));

        System.out.println("Convert Litre to Millilitre: " +
                volume1.convertTo(VolumeUnit.MILLILITRE));

        System.out.println("Convert Gallon to Litre: " +
                volume3.convertTo(VolumeUnit.LITRE));

        System.out.println("Add Volume (default unit): " +
                volume1.add(volume2));

        System.out.println("Add Volume (Gallon result): " +
                volume1.add(volume3, VolumeUnit.GALLON));


        // =========================
        // CROSS CATEGORY SAFETY
        // =========================
        System.out.println("\n===== CROSS CATEGORY =====");

        System.out.println("Length vs Weight equal? " +
                length1.equals(weight1));

        System.out.println("Weight vs Volume equal? " +
                weight1.equals(volume1));

        System.out.println("Length vs Volume equal? " +
                length1.equals(volume1));
    }
}