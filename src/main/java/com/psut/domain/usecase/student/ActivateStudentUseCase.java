package com.psut.domain.usecase.student;

import com.psut.exception.TechnicalValidationException;
import com.psut.model.shared.UserStatus;
import com.psut.model.student.Student;
import com.psut.service.StudentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ActivateStudentUseCase {
    private final StudentService studentService;

    public Student execute(Student student) {
        validate(student);
        student.setStatus(UserStatus.ACTIVE);
        return studentService.update(student);
    }

    private void validate(Student student) {
        if (UserStatus.ACTIVE.equals(student.getStatus())) {
            throw new TechnicalValidationException("student.is.already.active");
        }
    }
}
