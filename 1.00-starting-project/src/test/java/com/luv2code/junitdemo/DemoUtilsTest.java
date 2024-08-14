package com.luv2code.junitdemo;

import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)
//@TestMethodOrder(MethodOrderer.Random.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoUtilsTest {
    DemoUtils demoUtils;

    @BeforeEach
    void setUpBeforeEach(){
        demoUtils = new DemoUtils();
        System.out.println("@BeforeEach Executes before the execution of each test method");
    }

    @Test
    @DisplayName("Multiply")
    void testMultiply(){
        assertEquals(25, demoUtils.multiply(5,5), "5 * 5 must equal 25");
    }

    @Test
    @DisplayName("Equals And Not Equals")
    @Order(1)
    void testEqualsAndNotEquals(){
        System.out.println("Running Test: testEqualsAndNotEquals()");

        assertEquals(6,demoUtils.add(2,4),"2+4 must be 6");
        assertNotEquals(6,demoUtils.add(1,9),"1+9 must not be 6");
    }

    @Test
    @DisplayName("Null Not Null")
    @Order(2)
    void testNullAndNotNull(){
        System.out.println("Running Test: testNullAndNotNull()");

        String str1 = null;
        String str2 = "luv2code";

        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
    }

    @Test
    @DisplayName("Same And Not Same")
    void sameAndNotSame(){
        String str = "luv2code";
        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(),"Object should refer to the same object");
        assertNotSame(str,demoUtils.getAcademy(), "Object should not refer to the same object");
    }

    @Test
    @DisplayName("True Or False")
    void testTrueOrFalse(){
        int one = 10;
        int two = 5;
        assertTrue(demoUtils.isGreater(one, two), "This Should Return True");
        assertFalse(demoUtils.isGreater(two, one), "This Should Return False");
    }

    @Test
    @DisplayName("Array Equals")
    void testArrayEquals(){
        String[] stringArray = {"A", "B", "C"};

        assertArrayEquals(stringArray, demoUtils.getFirstThreeLettersOfAlphabet(), "Array should be the same");
    }


    @Test
    @DisplayName("Iterable Equals")
    void testIterableEquals(){
        List<String> theList = List.of("luv", "2", "code");

        assertIterableEquals(theList, demoUtils.getAcademyInList(), "List should be the same");
    }

    @Test
    @DisplayName("Lines Match")
    void testLinesMatch(){
        List<String> theList = List.of("luv", "2", "code");

        assertLinesMatch(theList, demoUtils.getAcademyInList(), "List should be the same");
    }

    @Test
    @DisplayName("Throws And Does Not Throw")
    void testThrowsAndDoesNotThrow(){
        assertThrows(Exception.class, () ->{demoUtils.throwException(-5);}, "should throw exception");
        assertDoesNotThrow(() ->{demoUtils.throwException(5);}, "should not throw exception");
    }

    @Test
    @DisplayName("TimeOut")
    void testTimeOut(){
        assertTimeoutPreemptively(Duration.ofSeconds(6), () -> {demoUtils.checkTimeout();}, "Method Should execute in 3 seconds");
    }


//    @AfterEach
//    void tearDownAfterEach(){
//        System.out.println("@AfterEach Executes After the execution of each test method \n");
//    }
//
//    @BeforeAll
//    static void setUpBeforeEachClass(){
//        System.out.println("@BeforeAll Executes Only Once Before All Methods");
//    }
//
//    @AfterAll
//    static void tearDownAfterEachClass(){
//        System.out.println("@AfterAll Executes Only Once After All Methods");
//    }

}
