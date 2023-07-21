package com.example.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTests {
    @Test
    @DisplayName("더하기 테스트")
    public void additionTest(){
        Calculator calculator = new Calculator();

        assertEquals(5, calculator.add(2,3));
        assertNotEquals(5, calculator.add(2,2));
    }

    @Test
    @DisplayName("나누기 테스트")
    public void divisionTest(){
        Calculator calculator = new Calculator();

        assertEquals(2, calculator.divide(4,2));
        // 잘못된 경우 테스트
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(5,0));

    }

    private class Calculator {

        public int add(int a, int b) {
            //return 5; // 가짜 구현
            return a+b; // 삼각 측량
        }

        public int divide(int a, int b) {
            if (b == 0)
                throw new IllegalArgumentException("division by zero");
            return a/b;
        }
    }
}
