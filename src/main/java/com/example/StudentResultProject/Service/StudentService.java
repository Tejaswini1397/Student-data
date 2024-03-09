package com.example.StudentResultProject.Service;

import com.example.StudentResultProject.Model.Student;
import com.example.StudentResultProject.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Student addStudent(Student student) {

        return studentRepository.save(student);
    }


    public Optional<Student> getStudent(int rollNo) {
        return studentRepository.findById(rollNo);
    }

    public Student updateStudent(int rollNo,String newName) {
        Student existingStudent = studentRepository.findById(rollNo)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + rollNo));

        existingStudent.setName(newName);

        return studentRepository.save(existingStudent);
    }

    public String deleteStudent(int rollNo) {
      if(!studentRepository.existsById(rollNo)){
          throw new RuntimeException("Student Not Found with Id: "+ rollNo);

      }
        studentRepository.deleteById(rollNo);
      return "Student will be deleted successfully";

    }
}
