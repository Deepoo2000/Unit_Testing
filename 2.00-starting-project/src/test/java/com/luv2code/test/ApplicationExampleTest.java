package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
//@SpringBootConfiguration
public class ApplicationExampleTest {
    private static int count = 0;

    @Value("${info.app.name}")
    private String appInfo;

    @Value("${info.app.description}")
    private String appDescription;

    @Value("${info.app.version}")
    private String appVersion;

    @Value("${info.school.name}")
    private String appName;

    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;


    @BeforeEach
    public void beforeEach(){
        count++;
        System.out.println("Testing: " + appInfo + " which is " + appDescription +
                " version: " + appVersion + ". execution of test method " + count);

        student.setFirstname("Ahmed");
        student.setLastname("El-Deeb");
        student.setEmailAddress("ahmed@gmail.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 85.0,76.50, 91.75)));
        student.setStudentGrades(studentGrades);
    }

    @DisplayName("add grade results for student grades")
    @Test
    void addGradeResultsForStudentGrades(){
        assertEquals(353.25, studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()
        ));
    }

    @DisplayName("add grade results for student grades Assert Not Equals")
    @Test
    void addGradeResultsForStudentGradesAssertNotEquals(){
        assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()
        ));
    }

    @DisplayName("Is Grade Greater")
    @Test
    public void isGradeGreaterStudentGrades(){
        assertTrue(studentGrades.isGradeGreater(90, 75), "Failure Should Be True");
    }

    @DisplayName("Is Grade Greater False")
    @Test
    public void isGradeGreaterStudentGradesAssertFalse(){
        assertFalse(studentGrades.isGradeGreater(58, 75), "Failure Should Be false");
    }

    @DisplayName("Check Null For Student Grades")
    @Test
    public void checkNullForStudentGrades(){
        assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()),
                "Object Should not be null");
    }


    @DisplayName("Create Student Without Grade Init")
    @Test
    public void createStudentWithoutGradeInit(){
        CollegeStudent studentTwo = context.getBean(CollegeStudent.class);
//        System.out.println("The Second Student " + studentTwo);
        studentTwo.setFirstname("Shimaa");
        studentTwo.setLastname("Alaa");
        studentTwo.setEmailAddress("shimaa@gmail.com");

        assertNotNull(studentTwo.getFirstname());
        assertNotNull(studentTwo.getLastname());
        assertNotNull(studentTwo.getEmailAddress());

        assertNull(studentGrades.checkNull(studentTwo.getStudentGrades()));
    }

    @DisplayName("Verfiy Stundents Are Prototypes")
    @Test
    public void verfiyStundentsArePrototypes(){
        CollegeStudent studentTwo = context.getBean(CollegeStudent.class);
        assertNotSame(student, studentTwo);
    }

    @DisplayName("Find Grade Point Average")
    @Test
    public void findGradePointAverage(){
        assertAll("Testing All AssertEquals",
                () -> assertEquals(353.25,studentGrades.addGradeResultsForSingleClass(
                        student.getStudentGrades().getMathGradeResults())),
                () -> assertEquals(88.31,studentGrades.findGradePointAverage(
                        student.getStudentGrades().getMathGradeResults()
                )));
    }



}
