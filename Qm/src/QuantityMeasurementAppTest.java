@Test
public void testFeetEquality() {
    var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
    var q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
    assertTrue(q1.equals(q2));
}

@Test
public void testInchesEquality() {
    var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);
    var q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);
    assertTrue(q1.equals(q2));
}

@Test
public void testFeetInchesComparison() {
    var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
    var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
    assertTrue(q1.equals(q2));
}

@Test
public void testFeetInequality() {
    var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
    var q2 = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET);
    assertFalse(q1.equals(q2));
}

@Test
public void testInchesInequality() {
    var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.INCH);
    var q2 = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.INCH);
    assertFalse(q1.equals(q2));
}

@Test
public void testCrossUnitInequality() {
    var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
    var q2 = new QuantityMeasurementApp.QuantityLength(10.0, QuantityMeasurementApp.LengthUnit.INCH);
    assertFalse(q1.equals(q2));
}

@Test
public void yardEquals36Inches() {
    var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
    var q2 = new QuantityMeasurementApp.QuantityLength(36.0, QuantityMeasurementApp.LengthUnit.INCH);
    assertTrue(q1.equals(q2));
}

@Test
public void centimeterEquals393Point3701Inches() {
    var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
    var q2 = new QuantityMeasurementApp.QuantityLength(0.393701, QuantityMeasurementApp.LengthUnit.INCH);
    assertTrue(q1.equals(q2));
}

@Test
public void threeFeetEqualsOneYard() {
    var q1 = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);
    var q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
    assertTrue(q1.equals(q2));
}

@Test
public void thirtyPoint48CmEqualsOneFoot() {
    var q1 = new QuantityMeasurementApp.QuantityLength(30.48, QuantityMeasurementApp.LengthUnit.CENTIMETERS);
    var q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
    assertTrue(q1.equals(q2));
}

@Test
public void yardNotEqualToInches() {
    var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
    var q2 = new QuantityMeasurementApp.QuantityLength(30.0, QuantityMeasurementApp.LengthUnit.INCH);
    assertFalse(q1.equals(q2));
}

@Test
public void referenceEqualitySameObject() {
    var q = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
    assertTrue(q.equals(q));
}

@Test
public void equalsReturnsFalseForNull() {
    var q = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
    assertFalse(q.equals(null));
}