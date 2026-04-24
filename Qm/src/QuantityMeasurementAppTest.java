package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    double EPS = 1e-6;

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0, LengthUnit.FEET.convertToBaseUnit(1.0), EPS);
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0, LengthUnit.INCH.convertToBaseUnit(12.0), EPS);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), EPS);
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), EPS);
    }

    @Test
    void testConvertFromBaseUnit() {
        assertEquals(12.0, LengthUnit.INCH.convertFromBaseUnit(1.0), EPS);
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), EPS);
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), EPS);
    }

    @Test
    void testQuantityLength_Equality() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testQuantityLength_ConvertTo() {
        var q = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        var result = q.convertTo(LengthUnit.INCH);
        assertEquals(12.0, result.convertTo(LengthUnit.INCH).value, EPS);
    }

    @Test
    void testQuantityLength_Add() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);
        var result = q1.add(q2, LengthUnit.FEET);
        assertEquals(2.0, result.value, EPS);
    }

    @Test
    void testQuantityLength_AddWithTargetUnit() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);
        var result = q1.add(q2, LengthUnit.YARDS);
        assertEquals(0.666666, result.value, 1e-3);
    }

    @Test
    void testNullUnitThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityMeasurementApp.QuantityLength(1.0, null));
    }

    @Test
    void testInvalidValueThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityMeasurementApp.QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testRoundTripConversion() {
        double value = 5.0;
        double inches = QuantityMeasurementApp.convert(value, LengthUnit.FEET, LengthUnit.INCH);
        double back = QuantityMeasurementApp.convert(inches, LengthUnit.INCH, LengthUnit.FEET);
        assertEquals(value, back, EPS);
    }

    @Test
    void testCommutativity_Addition() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0, LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0, LengthUnit.INCH);

        var r1 = a.add(b, LengthUnit.YARDS);
        var r2 = b.add(a, LengthUnit.YARDS);

        assertEquals(r1.value, r2.value, EPS);
    }

    @Test
    void testZeroAddition() {
        var q1 = new QuantityMeasurementApp.QuantityLength(5.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(0.0, LengthUnit.INCH);
        assertEquals(5.0, q1.add(q2).value, EPS);
    }

    @Test
    void testNegativeAddition() {
        var q1 = new QuantityMeasurementApp.QuantityLength(5.0, LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(-2.0, LengthUnit.FEET);
        assertEquals(3.0, q1.add(q2).value, EPS);
    }
}