package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

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
            return new QuantityLength(targetUnit.fromFeet(this.toFeet()), targetUnit);
        }

        public QuantityLength add(QuantityLength other) {
            if (other == null) throw new IllegalArgumentException();
            double sumFeet = this.toFeet() + other.toFeet();
            return new QuantityLength(this.unit.fromFeet(sumFeet), this.unit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) throw new IllegalArgumentException();
            double sumFeet = this.toFeet() + other.toFeet();
            return new QuantityLength(targetUnit.fromFeet(sumFeet), targetUnit);
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
        return target.fromFeet(source.toFeet(value));
    }

    public static QuantityLength add(QuantityLength q1, QuantityLength q2) {
        if (q1 == null || q2 == null) throw new IllegalArgumentException();
        return q1.add(q2);
    }

    public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {
        if (q1 == null || q2 == null || targetUnit == null) throw new IllegalArgumentException();
        double sumFeet = q1.unit.toFeet(q1.value) + q2.unit.toFeet(q2.value);
        return new QuantityLength(targetUnit.fromFeet(sumFeet), targetUnit);
    }

    public static QuantityLength add(double v1, LengthUnit u1, double v2, LengthUnit u2, LengthUnit target) {
        if (u1 == null || u2 == null || target == null || !Double.isFinite(v1) || !Double.isFinite(v2))
            throw new IllegalArgumentException();
        double sumFeet = u1.toFeet(v1) + u2.toFeet(v2);
        return new QuantityLength(target.fromFeet(sumFeet), target);
    }

    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println(q1.add(q2, LengthUnit.FEET));
        System.out.println(q1.add(q2, LengthUnit.INCH));
        System.out.println(q1.add(q2, LengthUnit.YARDS));
    }
}