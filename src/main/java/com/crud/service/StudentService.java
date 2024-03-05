package com.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.entity.Student;
import com.crud.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	 private StudentRepository studentRepository;
	
	public List<Student> getAll(){
		return studentRepository.findAll();
	}
	
	public Student findById(Long id)
	{
		return studentRepository.findById(id).get();
	}
	
    public Student saveStudent(Student student)
    {
    	return studentRepository.save(student);
    }
    
    public void deleteStudent(Long id) 
    {
    	studentRepository.deleteById(id);
    }
    public Student updateStudent(Student student,Long id)
    {
    	Student oldStudent = studentRepository.findById(id).get();
    	
    	oldStudent.setId(id);
    	oldStudent.setName(student.getName());
    	oldStudent.setAttributes(student.getAttributes());
    	return studentRepository.save(oldStudent);
    }
    
    
    
    
    
	
}
