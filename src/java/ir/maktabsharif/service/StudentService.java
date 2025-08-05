package ir.maktabsharif.service;

import ir.maktabsharif.model.Student;
import ir.maktabsharif.repository.EnrollmentRepository;
import ir.maktabsharif.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentService {
    private StudentRepository studentRepository;
    private EnrollmentRepository enrollmentRepository;

    public StudentService(StudentRepository studentRepository, EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Student> findStudents (StudentFilter CustomFilter) {
        return studentRepository.findAllStudents().stream()
                .filter(CustomFilter::matches)  //student -> CustomFilter.matches(student)
                .collect(Collectors.toList());
    }

    public Long countStudents (Predicate<Student> predicate) {
        return studentRepository.findAllStudents().stream()
                .filter(predicate)
                .count();
    }


    public void printFilterGpaComputerScienceStudents() {
        List<Student> computerScienceStudents = findStudents(student ->
            student.getGpa() > 3.5 && student.getMajor().equals("Computer Science"));

        computerScienceStudents.forEach(System.out::println);
    }

    public Long countOlderYearStudents (Predicate<Student> yearFilter) {
        return countStudents(yearFilter);
    }


    public void processingStudents(Consumer<Student> consumer) {
        studentRepository.findAllStudents()
                .forEach(consumer);
    }
}
