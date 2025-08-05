package ir.maktabsharif.model;

import java.time.LocalDate;

public class Enrollment {
    private int studentId;
    private int courseId;
    private LocalDate enrollmentDate;
    private double grade;

    public Enrollment(int studentId, int courseId, LocalDate enrollmentDate, double grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                ", enrollmentDate=" + enrollmentDate +
                ", grade=" + grade +
                '}';
    }
}
