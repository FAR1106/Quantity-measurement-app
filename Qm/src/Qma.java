package com.apps.quantitymeasurement;

public class Qma {

    public enum WeightUnit {
        KILOGRAM(1.0),
        GRAM(0.001),
        POUND(0.453592);

        private final double toKg;

        WeightUnit(double toKg) {
            this.toKg = toKg;
        }

        public double toBase(double value) {
            return value * toKg;
        }

        public double fromBase(double baseValue) {
            return baseValue / toKg;
        }
    }

    public static class QuantityWeight {

        private final double value;
        private final WeightUnit unit;

        public QuantityWeight(double value, WeightUnit unit) {
            if (unit == null || !Double.isFinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.toBase(value);
        }

        public QuantityWeight convertTo(WeightUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException();
            return new QuantityWeight(targetUnit.fromBase(toBase()), targetUnit);
        }

        public QuantityWeight add(QuantityWeight other) {
            if (other == null) throw new IllegalArgumentException();
            double sum = this.toBase() + other.toBase();
            return new QuantityWeight(unit.fromBase(sum), unit);
        }

        public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
            if (other == null || targetUnit == null) throw new IllegalArgumentException();
            double sum = this.toBase() + other.toBase();
            return new QuantityWeight(targetUnit.fromBase(sum), targetUnit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || this.getClass() != obj.getClass()) return false;
            QuantityWeight other = (QuantityWeight) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBase());
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }
}