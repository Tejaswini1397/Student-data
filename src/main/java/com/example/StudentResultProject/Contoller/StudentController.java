package com.example.StudentResultProject.Contoller;

import com.example.StudentResultProject.Model.Student;
import com.example.StudentResultProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
    @GetMapping("/get_student/{roll_no}")
    public ResponseEntity<Object> getStudent(@PathVariable int roll_no ){
        // Retrieve student from the service
        Student student = studentService.getStudent(roll_no).orElse(null);

        if (student != null) {
            // If student is found, return it in the response
            return ResponseEntity.ok(student);
        } else {
            // If student is not found, return a custom response
            String errorMessage = "Student with Roll No " + roll_no + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
    @PutMapping("/update/{roll_no}")
    public Student updateStudent(@PathVariable int roll_no, @RequestParam String newName){
      return  studentService.updateStudent(roll_no,newName);

    }
    @DeleteMapping("/delete/{roll_no}")
    public String deleteStudent(@PathVariable int roll_no){
        return studentService.deleteStudent(roll_no);
    }
}
