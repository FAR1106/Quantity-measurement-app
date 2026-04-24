package com.apps.quantitymeasurement;

public class Qma {

    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.393701 / 12.0);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toFeet(double value) {
            return value * toFeet;
        }

        public double fromFeet(double value) {
            return value / toFeet;
        }
    }

    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException();
            double feetValue = this.toFeet();
            double converted = targetUnit.fromFeet(feetValue);
            return new QuantityLength(converted, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null || !Double.isFinite(value)) throw new IllegalArgumentException();
        double base = source.toFeet(value);
        return target.fromFeet(base);
    }

    public static double demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        return convert(value, from, to);
    }

    public static double demonstrateLengthConversion(QuantityLength quantity, LengthUnit to) {
        return quantity.convertTo(to).value;
    }

    public static boolean demonstrateLengthEquality(QuantityLength q1, QuantityLength q2) {
        return q1.equals(q2);
    }

    public static boolean demonstrateLengthComparison(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);
        return q1.equals(q2);
    }

    public static void main(String[] args) {
        System.out.println(convert(1.0, LengthUnit.FEET, LengthUnit.INCH));
        System.out.println(convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));
        System.out.println(convert(36.0, LengthUnit.INCH, LengthUnit.YARDS));
        System.out.println(convert(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCH));
    }
}