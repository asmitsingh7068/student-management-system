package com.asmit.student_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asmit.student_management.dto.StudentDTO;
import com.asmit.student_management.entity.Student;
import com.asmit.student_management.exception.StudentNotFoundException;
import com.asmit.student_management.repository.StudentRepository;
@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    // ✅ CREATE (DTO → Entity)
    public Student saveStudent(StudentDTO dto) {
    Student student = new Student();

    student.setName(dto.getName());
    student.setEmail(dto.getEmail());
    student.setCourse(dto.getCourse()); // 🔥 MOST IMPORTANT LINE

    return repository.save(student);
}

    // ✅ GET ALL
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // ✅ GET BY ID
    public Student getStudentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // ✅ UPDATE
    public Student updateStudent(Long id, StudentDTO dto) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());

        return repository.save(student);  // VERY IMPORTANT
    }

    // ✅ DELETE
    public void deleteStudent(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        repository.delete(student);
    }

}