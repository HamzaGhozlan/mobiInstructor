package com.psut.controllers;

import com.psut.models.student.Student;
import com.psut.models.student.UpdateStudentRequest;
import com.psut.repositories.StudentRepository;
import com.psut.usecases.students.ActivateStudentUseCase;
import com.psut.usecases.students.CreateStudentUseCase;
import com.psut.usecases.students.DeactivateStudentUseCase;
import com.psut.usecases.students.UpdateStudentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.psut.controllers.StudentsController.STUDENTS_BASE_URL;

@RequiredArgsConstructor
@RestController(STUDENTS_BASE_URL)
public class StudentsController {
    public static final String STUDENTS_BASE_URL = "/api/v1/students";

    private final StudentRepository studentRepository;
    private final CreateStudentUseCase createStudentUseCase;
    private final UpdateStudentUseCase updateStudentUseCase;
    private final DeactivateStudentUseCase deactivateStudentUseCase;
    private final ActivateStudentUseCase activateStudentUseCase;

    @GetMapping
    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public Student createStudent(Student student) {
        return createStudentUseCase.execute(student);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequest updateRequest) {
        Student student = studentRepository.findById(id);
        return updateStudentUseCase.execute(student, updateRequest);
    }

    @PostMapping("{id}/deactivate")
    public Student deactivateStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id);
        return deactivateStudentUseCase.execute(student);
    }

    @PostMapping("{id}/activate")
    public Student ActivateStudent(@PathVariable Long id) {
        Student student = studentRepository.findById(id);
        return activateStudentUseCase.execute(student);
    }

}
