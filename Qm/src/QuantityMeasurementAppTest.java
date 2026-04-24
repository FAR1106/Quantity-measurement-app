import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    double EPS = 1e-6;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(2.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(3.0, q1.add(q2).convertTo(QuantityMeasurementApp.LengthUnit.FEET).value, EPS);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(2.0, q1.add(q2).convertTo(QuantityMeasurementApp.LengthUnit.FEET).value, EPS);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        var q1 = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        var q2 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(24.0, q1.add(q2).convertTo(QuantityMeasurementApp.LengthUnit.INCH).value, EPS);
    }

    @Test
    void testAddition_YardPlusFeet() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.YARDS);
        var q2 = new QuantityMeasurementApp.QuantityLength(3.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(2.0, q1.add(q2).convertTo(QuantityMeasurementApp.LengthUnit.YARDS).value, EPS);
    }

    @Test
    void testAddition_Commutativity() {
        var a = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        var b = new QuantityMeasurementApp.QuantityLength(12.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(a.add(b).toString(), b.add(a).convertTo(a.unit).toString());
    }

    @Test
    void testAddition_WithZero() {
        var q1 = new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(0.0, QuantityMeasurementApp.LengthUnit.INCH);
        assertEquals(5.0, q1.add(q2).value, EPS);
    }

    @Test
    void testAddition_NegativeValues() {
        var q1 = new QuantityMeasurementApp.QuantityLength(5.0, QuantityMeasurementApp.LengthUnit.FEET);
        var q2 = new QuantityMeasurementApp.QuantityLength(-2.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertEquals(3.0, q1.add(q2).value, EPS);
    }

    @Test
    void testAddition_NullOperand() {
        var q1 = new QuantityMeasurementApp.QuantityLength(1.0, QuantityMeasurementApp.LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> q1.add(null));
    }
}