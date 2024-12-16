package com.spring.controllers;
import com.spring.dtos.StudentDTO;
import com.spring.dtos.SubjectDTO;
import com.spring.entities.Students;
import com.spring.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    // Add a new student
    @PostMapping
    public Students addStudent(@RequestBody Students student) {
        return studentService.addStudent(student);
    }
    // Get students by name
    @GetMapping("/name/{name}")
    public List<StudentDTO> getStudentByName(@PathVariable String name) {
        return studentService.getStudentByName(name);
    }
    // Get students by age
    @GetMapping("/age/{age}")
    public List<StudentDTO> getStudentByAge(@PathVariable int age) {
        return studentService.getStudentByAge(age);
    }
    // Get students by gender
    @GetMapping("/gender/{gender}")
    public List<StudentDTO> getStudentByGender(@PathVariable String gender) {
        return studentService.getStudentByGender(gender);
    }
    // Get all students with pagination
    @GetMapping("/all")
    public Page<StudentDTO> getAllStudents(@RequestParam int page, @RequestParam int size) {
        return studentService.getAllStudents(page, size);
    }

    //find student by id
    @GetMapping("/id/{id}")
    public StudentDTO getStudentById(@PathVariable int id){
        return studentService.getStudentById(id);
    }
  
    //deletes student by id
    @DeleteMapping("/del/{id}")
    public void deleteStudentById(@PathVariable int id){
        studentService.deleteStudentById(id);
    }


    // Get subjects by name
    @GetMapping("/subjects/{name}")
    public List<SubjectDTO> getSubjectsByName(@PathVariable String name) {
        return studentService.findSubjectsByName(name);
    }
}

