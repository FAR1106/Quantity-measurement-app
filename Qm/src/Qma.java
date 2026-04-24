package com.apps.quantitymeasurement;

public class Qma {

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.convertToBaseUnit(value);
        }

        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException();
            double base = toBase();
            return new QuantityLength(targetUnit.convertFromBaseUnit(base), targetUnit);
        }

        public QuantityLength add(QuantityLength other) {
            if (other == null) throw new IllegalArgumentException();
            double sum = this.toBase() + other.toBase();
            return new QuantityLength(unit.convertFromBaseUnit(sum), unit);
        }

        public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) throw new IllegalArgumentException();
            double sum = this.toBase() + other.toBase();
            return new QuantityLength(targetUnit.convertFromBaseUnit(sum), targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null || !Double.isFinite(value)) throw new IllegalArgumentException();
        double base = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(base);
    }

    public static QuantityLength add(QuantityLength q1, QuantityLength q2, LengthUnit targetUnit) {
        if (q1 == null || q2 == null || targetUnit == null) throw new IllegalArgumentException();
        double sum = q1.unit.convertToBaseUnit(q1.value) + q2.unit.convertToBaseUnit(q2.value);
        return new QuantityLength(targetUnit.convertFromBaseUnit(sum), targetUnit);
    }

    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println(q1.convertTo(LengthUnit.INCH));
        System.out.println(q1.add(q2, LengthUnit.FEET));
        System.out.println(q1.add(q2, LengthUnit.YARDS));
    }
}