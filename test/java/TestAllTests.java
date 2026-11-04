import org.example.ArithmeticOperations;
import org.example.CompareNumbers;
import org.example.FactorialCalculator;
import org.example.TriangleArea;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestAllTests {

    @Test
    void testFactorialCalculator() {
        assertEquals(1, FactorialCalculator.factorial(0));
        assertEquals(1, FactorialCalculator.factorial(1));
        assertEquals(120, FactorialCalculator.factorial(5));
        assertThrows(IllegalArgumentException.class, () -> FactorialCalculator.factorial(-5));
    }

    @Test
    void testTriangleArea() {
        assertEquals(10.0, TriangleArea.Area(4, 5));
        assertThrows(IllegalArgumentException.class, () -> TriangleArea.Area(-1, 5));
    }

    @Test
    void testArithmeticOperations() {
        assertEquals(10, ArithmeticOperations.add(7, 3));
        assertEquals(4, ArithmeticOperations.subtract(7, 3));
        assertEquals(21, ArithmeticOperations.multiply(7, 3));
        assertEquals(2.5, ArithmeticOperations.divide(5, 2));
        assertThrows(ArithmeticException.class, () -> ArithmeticOperations.divide(5, 0));
    }

    @Test
    void testCompareNumbers() {
        assertEquals(1, CompareNumbers.compare(10, 5));
        assertEquals(0, CompareNumbers.compare(5, 5));
        assertEquals(-1, CompareNumbers.compare(3, 5));
    }
}

