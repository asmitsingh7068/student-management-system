package com.asmit.student_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asmit.student_management.dto.StudentDTO;
import com.asmit.student_management.entity.Student;
import com.asmit.student_management.entity.User;
import com.asmit.student_management.repository.UserRepository;
import com.asmit.student_management.service.StudentService;
import com.asmit.student_management.util.JwtUtil;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/students")
public class TestController {

    @Autowired
    private JwtUtil jwt;

    @Autowired
    private StudentService service; // ✅ ADD THIS

    @Autowired
private UserRepository userRepo;

    // API Test
    @GetMapping("/home")
    public String home() {
        return "Student API Running 🚀";
    }

    // CREATE
    @PostMapping
    public Student createStudent(@Valid @RequestBody StudentDTO dto) {
        return service.saveStudent(dto);
    }

    // GET ALL
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,
            @Valid @RequestBody StudentDTO dto) {
        return service.updateStudent(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return "Student deleted successfully";
    }

    // 🔥 JWT TEST
    @GetMapping("/test")
    public String testJwt() {
        return jwt.generateToken("bubu");
    }

    // 🔥 EXTRACT USERNAME
    @GetMapping("/extract")
    public String extract() {
        try {
            String token = jwt.generateToken("bubu");
            return jwt.extractUsername(token);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    // 🔥 LOGIN API
    @PostMapping("/login")
public String login(@RequestBody User user) {

    User dbUser = userRepo.findByUsername(user.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found ❌"));

    if (dbUser.getPassword().equals(user.getPassword())) {
        return jwt.generateToken(user.getUsername());
    }

    return "Invalid Credentials ❌";
}

    

@PostMapping("/signup")
public String signup(@RequestBody User user) {
    userRepo.save(user);
    return "User Registered Successfully ✅";
}
}