package ir.maktabsharif;

import ir.maktabsharif.model.Course;
import ir.maktabsharif.model.Enrollment;
import ir.maktabsharif.model.Student;
import ir.maktabsharif.repository.CourseRepository;
import ir.maktabsharif.repository.EnrollmentRepository;
import ir.maktabsharif.repository.StudentRepository;
import ir.maktabsharif.service.CourseService;
import ir.maktabsharif.service.EnrollmentService;
import ir.maktabsharif.service.StudentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        StudentService studentService = new StudentService(studentRepository,enrollmentRepository);
        CourseService courseService = new CourseService(courseRepository,enrollmentRepository);
        EnrollmentService enrollmentService = new EnrollmentService(studentRepository,courseRepository,enrollmentRepository);

        while (true) {
            System.out.println("--------------------------------------");
            System.out.println("College Management System:");
            System.out.println("1. Insert sample students");
            System.out.println("2. Insert sample courses");
            System.out.println("3. Insert sample enrollment");
            System.out.println("4. Add student");
            System.out.println("5. Add course");
            System.out.println("6. Add enrollment");
            System.out.println("7. Update student");
            System.out.println("8. Update course");
            System.out.println("9. Update enrollment");
            System.out.println("10. Delete student");
            System.out.println("11. Delete course");
            System.out.println("12. Delete enrollment");
            System.out.println("13. Print all students with GPA > 3.5 AND enrolled in “Computer Science");
            System.out.println("14. List all students who enrolled in a given course after a given date");
            System.out.println("15. Count all students older than a certain year");
            System.out.println("16. Print courses in which the average grade is above your number");
            System.out.println("17. Print student names in uppercase");
            System.out.println("18. Exit");
            System.out.println("-------------------------------------------");
            System.out.print("Please Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    studentRepository.insertSampleStudents();
                    break;
                case "2":
                    courseRepository.insertSampleCourses();
                    break;
                case "3":
                    enrollmentRepository.insertSampleEnrollment();
                    break;
                case "4":
                    System.out.println("Enter student id:");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter major:");
                    String major = scanner.nextLine();
                    System.out.println("Enter year:");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter gpa:");
                    double gpa = scanner.nextDouble();
                    scanner.nextLine();
                    try {
                        studentRepository.addStudent(new Student(studentId,name,major,year,gpa));
                        System.out.println("Student added successfully");
                    } catch (RuntimeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "5":
                    System.out.println("Enter course id:");
                    int courseId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter department:");
                    String department = scanner.nextLine();
                    System.out.println("Enter credits:");
                    int credits = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        courseRepository.addCourse(new Course(courseId,title,department,credits));
                        System.out.println("Course added successfully");
                    } catch (RuntimeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "6":
                    System.out.println("Enter student id:");
                    int studentIdEnroll = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter course id:");
                    int courseIdEnroll = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter enrollmentDate:");
                    String date = scanner.nextLine();
                    LocalDate enrollmentDate = LocalDate.parse(date);
                    System.out.println("Enter grade:");
                    double grade = scanner.nextDouble();
                    scanner.nextLine();
                    try {
                        enrollmentRepository.addEnrollment(new Enrollment(studentIdEnroll,courseIdEnroll,enrollmentDate,grade));
                        System.out.println("Enrollment added successfully");
                    } catch (RuntimeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "7":
                    System.out.println("Enter student id:");
                    int studentIdUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter name:");
                    String nameUpdate = scanner.nextLine();
                    System.out.println("Enter major:");
                    String majorUpdate = scanner.nextLine();
                    System.out.println("Enter year:");
                    int yearUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter gpa:");
                    double gpaUpdate = scanner.nextDouble();
                    scanner.nextLine();
                    try {
                        studentRepository.updateStudent(new Student(studentIdUpdate,nameUpdate,majorUpdate,yearUpdate,gpaUpdate));
                        System.out.println("Student updated successfully");
                    } catch (RuntimeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "8":
                    System.out.println("Enter course id:");
                    int courseIdUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter title:");
                    String titleUpdate = scanner.nextLine();
                    System.out.println("Enter department:");
                    String departmentUpdate = scanner.nextLine();
                    System.out.println("Enter credits:");
                    int creditsUpdate = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        courseRepository.updateCourse(new Course(courseIdUpdate,titleUpdate,departmentUpdate,creditsUpdate));
                        System.out.println("Course updated successfully");
                    } catch (RuntimeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "9":
                    System.out.println("Enter student id:");
                    int studentIdEnrollUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter course id:");
                    int courseIdEnrollUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter enrollmentDate:");
                    String dateUpdate = scanner.nextLine();
                    LocalDate enrollmentDateUpdate = LocalDate.parse(dateUpdate);
                    System.out.println("Enter grade:");
                    double gradeUpdate = scanner.nextDouble();
                    scanner.nextLine();
                    try {
                        enrollmentRepository.UpdateEnrollment(new Enrollment(studentIdEnrollUpdate,courseIdEnrollUpdate,enrollmentDateUpdate,gradeUpdate));
                        System.out.println("Enrollment updated successfully");
                    } catch (RuntimeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "10":
                    System.out.println("Enter student id to delete:");
                    int studentIdDelete = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        studentRepository.deleteStudent(studentIdDelete);
                        System.out.println("Student deleted successfully");
                    } catch (RuntimeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "11":
                    System.out.println("Enter course id to delete:");
                    int courseIdDelete = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        courseRepository.deleteCourse(courseIdDelete);
                        System.out.println("Course deleted successfully");
                    } catch (RuntimeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "12":
                    System.out.println("Enter student id enroll to delete:");
                    int studentIdEnrollDelete = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter course id enroll to delete:");
                    int courseIdEnrollDelete = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        enrollmentRepository.DeleteEnrollment(studentIdEnrollDelete,courseIdEnrollDelete);
                        System.out.println("Enrollment deleted successfully");
                    } catch (RuntimeException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "13":
                    studentService.printFilterGpaComputerScienceStudents();
                    break;
                case "14":
                    System.out.println("Enter course id:");
                    int courseIdToFind = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter Date:");
                    String dateString = scanner.nextLine();
                    LocalDate localDate = LocalDate.parse(dateString);
                    List<Student> studentList = enrollmentService.findStudentsEnrolledAfter(e -> e.getCourseId() == courseIdToFind && e.getEnrollmentDate().isAfter(localDate));
                    studentList.forEach(System.out::println);
                    break;
                case "15":
                    System.out.println("Enter year:");
                    int yearToCompare = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(studentService.countOlderYearStudents(student ->
                            student.getYear() > yearToCompare));
                    break;
                case "16":
                    System.out.println("Enter grade:");
                    double gradeToCompare = scanner.nextDouble();
                    scanner.nextLine();
                    courseService.printCoursesWithAverageGradeAbove(gradeToCompare);
                    break;
                case "17":
                    studentService.processingStudents(s ->
                            System.out.println(s.getName().toUpperCase()));
                    break;
                case "18":
                    System.out.println("Exit the College Management Service!");
                    return;
                default:
                    System.out.println("Please enter choice between 1 to 18!");
            }

        }
    }
}
