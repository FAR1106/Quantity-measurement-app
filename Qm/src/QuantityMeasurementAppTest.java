import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    double EPS = 1e-6;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(2.0, q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET).value, EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(24.0, q1.add(q2, QuantityMeasurementApp.LengthUnit.INCH).value, EPS);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(0.666666, q1.add(q2, QuantityMeasurementApp.LengthUnit.YARDS).value, 1e-3);
    }

    @Test
    void testAddition_Commutativity_WithTargetUnit() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        var r1 = a.add(b, QuantityMeasurementApp.LengthUnit.YARDS);
        var r2 = b.add(a, QuantityMeasurementApp.LengthUnit.YARDS);

        assertEquals(r1.value, r2.value, EPS);
    }

    @Test
    void testAddition_TargetUnit_Null() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertThrows(IllegalArgumentException.class, () -> q1.add(q2, null));
    }
}