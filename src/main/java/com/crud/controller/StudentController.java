package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.crud.entity.Student;
import com.crud.service.StudentService;

import java.util.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) {
       Student result = studentService.findById(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Student> create(@RequestBody Student student) {
    	Student saveStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(saveStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        
        Student result = studentService.updateStudent(student,id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
       
        studentService.deleteStudent(id);
       return "student deleted successfully";
    }
}
