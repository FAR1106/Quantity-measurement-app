import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Qma {

    @Test
    void testEquality_SameValue() {
        assertTrue(QuantityMeasurementApp.compareFeet(1.0, 1.0));
        assertTrue(QuantityMeasurementApp.compareInches(1.0, 1.0));
    }

    @Test
    void testEquality_DifferentValue() {
        assertFalse(QuantityMeasurementApp.compareFeet(1.0, 2.0));
        assertFalse(QuantityMeasurementApp.compareInches(1.0, 2.0));
    }

    @Test
    void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertFalse(f.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertTrue(i.equals(i));
    }
}