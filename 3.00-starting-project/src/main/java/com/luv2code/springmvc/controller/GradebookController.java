package com.luv2code.springmvc.controller;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradebookController {

    @Autowired
    private Gradebook gradebook;

	@Autowired
	private StudentAndGradeService studentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getStudents(Model m) {
		Iterable<CollegeStudent> collegeStudent = studentService.getGradeBook();
		m.addAttribute("students", collegeStudent);
		return "index";
    }

    @PostMapping(value = "/")
    public String createStudent(@ModelAttribute("student") CollegeStudent student, Model m){
        studentService.createStudent(student.getFirstname(), student.getLastname(),
                student.getEmailAddress());
        Iterable<CollegeStudent> collegeStudent = studentService.getGradeBook();
        m.addAttribute("students", collegeStudent);

        return "index";
    }

    @GetMapping("/delete/student/{id}")
    public String deleteStudent(@PathVariable int id, Model m){

        if(!studentService.checkIfStudentIsNull(id))return "error";

        studentService.deleteStudent(1);
        Iterable<CollegeStudent> collegeStudent = studentService.getGradeBook();
        m.addAttribute("students", collegeStudent);

        return "index";
    }

    @GetMapping("/studentInformation/{id}")
    public String studentInformation(@PathVariable int id, Model m) {
        if(!studentService.checkIfStudentIsNull(id))return "error";

        studentService.configureStudentInformationModel(id, m);

        return "studentInformation";
    }

    @PostMapping(value = "/grades")
    public String createGrade(@RequestParam("grade") double grade,
                              @RequestParam("gradeType") String gradeType,
                              @RequestParam("studentId") int studentId,
                              Model m){
        if(!studentService.checkIfStudentIsNull(studentId))return "error";
        boolean success = studentService.createGrade(grade, studentId, gradeType);

        if(!success) return "error";

        studentService.configureStudentInformationModel(studentId, m);

        return "studentInformation";
    }

    @GetMapping("/grades/{id}/{gradeType}")
    public String deleteGrade(@PathVariable("id") int id,
                            @PathVariable("gradeType") String gradeType, Model m){
        int studentId = studentService.deleteGrade(id, gradeType);
        if(studentId == 0) return "error";

        studentService.configureStudentInformationModel(studentId, m);

        return "studentInformation";
    }




}
