package com.luv2code.junitdemo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class ConditionalTest {

    @Test
    @Disabled("Don't run until JIRA #123 is resolved")
    void basicTest(){

    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testForWindowsOnly(){

    }

    @Test
    @EnabledOnOs({OS.MAC, OS.WINDOWS})
    void testForWindowsAndMacOnly(){

    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testForMacOnly(){

    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void testForLinuxOnly(){

    }


    @Test
    @EnabledOnJre(JRE.JAVA_18)
    void testForJava18(){}

    @Test
    @EnabledOnJre(JRE.JAVA_17)
    void testForJava17(){}

    @Test
    @EnabledOnJre(JRE.JAVA_13)
    void testForJava13(){}

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testForJava8(){}

    @Test
    @EnabledForJreRange(min=JRE.JAVA_8, max=JRE.JAVA_17)
    void testForJavaRange(){}

    @Test
    @EnabledForJreRange(min=JRE.JAVA_11)
    void testForJavaRangeMin(){}

    @Test
    @EnabledIfEnvironmentVariable(named = "LUV2CODE_ENV",matches = "DEV")
    void testForDevEnvironment(){}

    @Test
    @EnabledIfSystemProperty(named = "LUV2CODE_SYS_PROP",matches = "CI_CD_DEPLOY")
    void testForSystemProperty(){}

}
