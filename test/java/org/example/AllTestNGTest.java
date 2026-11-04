package org.example;
import org.example.ArithmeticOperations;
import org.example.CompareNumbers;
import org.example.FactorialCalculator;
import org.example.TriangleArea;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AllTestNGTest {
    @Test
    public void testArithmeticOperations() {
        assertEquals(ArithmeticOperations.add(6, 4), 10);
        assertEquals(ArithmeticOperations.subtract(6, 4), 2);
        assertEquals(ArithmeticOperations.multiply(6, 4), 24);
        assertEquals(ArithmeticOperations.divide(6, 4), 1.5, 0.001);
    }
    @Test
    public void testFactorial() {
        Assert.assertEquals(FactorialCalculator.factorial(1) , 1);
        Assert.assertEquals(FactorialCalculator.factorial(5) , 120);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialException() {
        FactorialCalculator.factorial(-1) ;
    }

    @Test
    public void testTriangleArea() {
        assertEquals(TriangleArea.Area(4, 5) , 10.0, 0.001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTriangleAreaException() {
        TriangleArea.Area(-1, 5) ;
    }


    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivisionByZero() {
        ArithmeticOperations.divide(6, 0);
    }

    @Test
    public void testNumberComparison() {
        assertEquals(CompareNumbers.compare(10, 5), 1);
        assertEquals(CompareNumbers.compare(3, 7), -1);
        assertEquals(CompareNumbers.compare(4, 4), 0);
    }
}

