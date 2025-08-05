package ir.maktabsharif.service;

import ir.maktabsharif.model.Course;
import ir.maktabsharif.model.Enrollment;
import ir.maktabsharif.repository.CourseRepository;
import ir.maktabsharif.repository.EnrollmentRepository;

import java.util.Map;
import java.util.stream.Collectors;

public class CourseService {
    private CourseRepository courseRepository;
    private EnrollmentRepository enrollmentRepository;

    public CourseService (CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public void printCoursesWithAverageGradeAbove (double averageGrade) {
        Map<Integer, Double> courseIdAverages = enrollmentRepository.findAllEnrollments().stream()
                .collect(Collectors.groupingBy(
                        Enrollment::getCourseId,
                        Collectors.averagingDouble(Enrollment::getGrade)
                ));

        courseIdAverages.entrySet().stream()
                .filter(entry -> entry.getValue() > averageGrade)
                .forEach(entry -> {
                    Course course = courseRepository.findCourseByID(entry.getKey());
                    System.out.println(course.getTitle() + "-> Average Grade: " + entry.getValue());
                });
    }


}
