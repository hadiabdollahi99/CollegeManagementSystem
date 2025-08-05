package ir.maktabsharif.service;

import ir.maktabsharif.model.Enrollment;
import ir.maktabsharif.model.Student;
import ir.maktabsharif.repository.CourseRepository;
import ir.maktabsharif.repository.EnrollmentRepository;
import ir.maktabsharif.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EnrollmentService {
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private EnrollmentRepository enrollmentRepository;
    public EnrollmentService(StudentRepository studentRepository, CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }


    public List<Student> findStudentsEnrolledAfter(Predicate<Enrollment> courseAfterDateFilter) {
        return enrollmentRepository.findAllEnrollments().stream()
                .filter(courseAfterDateFilter)
                .map(e -> studentRepository.findStudentByID(e.getStudentId()))
                .collect(Collectors.toList());
    }


}
