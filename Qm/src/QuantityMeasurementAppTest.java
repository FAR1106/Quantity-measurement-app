package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    double EPS = 1e-6;

    @Test
    void testEquality_KgToGram() {
        var q1 = new QuantityMeasurementApp.QuantityWeight(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);
        var q2 = new QuantityMeasurementApp.QuantityWeight(1000.0, QuantityMeasurementApp.WeightUnit.GRAM);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testEquality_KgToPound() {
        var q1 = new QuantityMeasurementApp.QuantityWeight(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);
        var q2 = new QuantityMeasurementApp.QuantityWeight(2.20462, QuantityMeasurementApp.WeightUnit.POUND);
        assertTrue(q1.equals(q2));
    }

    @Test
    void testConversion_KgToGram() {
        var q = new QuantityMeasurementApp.QuantityWeight(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);
        assertEquals(1000.0, q.convertTo(QuantityMeasurementApp.WeightUnit.GRAM).value, EPS);
    }

    @Test
    void testConversion_PoundToKg() {
        var q = new QuantityMeasurementApp.QuantityWeight(2.20462, QuantityMeasurementApp.WeightUnit.POUND);
        assertEquals(1.0, q.convertTo(QuantityMeasurementApp.WeightUnit.KILOGRAM).value, 1e-3);
    }

    @Test
    void testAddition_SameUnit() {
        var q1 = new QuantityMeasurementApp.QuantityWeight(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);
        var q2 = new QuantityMeasurementApp.QuantityWeight(2.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);
        assertEquals(3.0, q1.add(q2).value, EPS);
    }

    @Test
    void testAddition_CrossUnit() {
        var q1 = new QuantityMeasurementApp.QuantityWeight(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);
        var q2 = new QuantityMeasurementApp.QuantityWeight(1000.0, QuantityMeasurementApp.WeightUnit.GRAM);
        assertEquals(2.0, q1.add(q2).value, EPS);
    }

    @Test
    void testAddition_TargetUnit() {
        var q1 = new QuantityMeasurementApp.QuantityWeight(1.0, QuantityMeasurementApp.WeightUnit.KILOGRAM);
        var q2 = new QuantityMeasurementApp.QuantityWeight(1000.0, QuantityMeasurementApp.WeightUnit.GRAM);
        assertEquals(2000.0, q1.add(q2, QuantityMeasurementApp.WeightUnit.GRAM).value, EPS);
    }

    @Test
    void testNullHandling() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.QuantityWeight(1.0, null));
    }
}