package com.luv2code.tdd;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizzBuzzTest {

    @Test
    @DisplayName("Divisible By Three")
    @Order(1)
    void testForDivisibleByThree(){
        String expect = "Fizz";
        assertEquals(expect, FizzBuzz.compute(3), "Should Return Fizz");
    }

    @Test
    @DisplayName("Divisible By Five")
    @Order(2)
    void testForDivisibleByFive(){
        String expect = "Buzz";
        assertEquals(expect, FizzBuzz.compute(5), "Should Return Buzz");
    }

    @Test
    @DisplayName("Divisible By Three And Five")
    @Order(3)
    void testForDivisibleByThreeAndFive(){
        String expect = "FizzBuzz";
        assertEquals(expect, FizzBuzz.compute(15), "Should Return FizzBuzz");
    }

    @Test
    @DisplayName("Not Divisible By Three And Five")
    @Order(4)
    void testForNotDivisibleByThreeOrFive(){
        String expect = "1";
        assertEquals(expect, FizzBuzz.compute(1), "Should Return 1");
    }

    @ParameterizedTest(name = "value={0}, expected={1}")
    @CsvFileSource(resources = "/small-test-data.csv")
    @DisplayName("Testing With Small Data File")
    @Order(5)
    void testSmallDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));
    }

    @ParameterizedTest(name = "value={0}, expected={1}")
    @CsvFileSource(resources = "/medium-test-data.csv")
    @DisplayName("Testing With Medium Data File")
    @Order(6)
    void testMediumDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));
    }

    @ParameterizedTest(name = "value={0}, expected={1}")
    @CsvFileSource(resources = "/large-test-data.csv")
    @DisplayName("Testing With Large Data File")
    @Order(7)
    void testLargeDataFile(int value, String expected){
        assertEquals(expected, FizzBuzz.compute(value));
    }

}
