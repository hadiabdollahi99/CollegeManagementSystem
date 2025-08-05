package ir.maktabsharif.repository;

import ir.maktabsharif.utils.DBUtils;
import ir.maktabsharif.model.Enrollment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EnrollmentRepository {

    public List<Enrollment> findAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "select * from enrollment";
        try (
                Statement stmt = DBUtils.getConnection().createStatement();
                ResultSet result = stmt.executeQuery(sql))
        {
            while (result.next()) {
                int studentId = result.getInt(1);
                int courseId = result.getInt(2);
                java.sql.Date enrollmentDate = result.getDate(3);
                double grade = result.getDouble(4);
                enrollments.add(new Enrollment(studentId, courseId, enrollmentDate.toLocalDate(), grade));
            }
            return enrollments;
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to find students", exception);
        }
    }

    public Enrollment findEnrollmentByID (int studentId, int courseId) {
        String sql = "select * from enrollment where student_id = ? and course_id = ?";
        try (
                PreparedStatement prepared = DBUtils.getConnection().prepareStatement(sql))
        {
            prepared.setInt(1, studentId);
            prepared.setInt(2, courseId);
            ResultSet result = prepared.executeQuery();
            if (result.next()) {
                java.sql.Date enrollmentDate = result.getDate(3);
                double grade = result.getDouble(4);
                return new Enrollment(studentId, courseId, enrollmentDate.toLocalDate(), grade);
            }
            return null;
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to find student by this id", exception);
        }
    }

    public void createTableEnrollment() {
        String createEnrollmentTable = "create table enrollment (\n" +
                "    student_id int,\n" +
                "    course_id int,\n" +
                "    enrollment_date date,\n" +
                "    grade decimal,\n" +
                "    foreign key (student_id) references student(id),\n" +
                "    foreign key (course_id) references course(id)\n" +
                ")";
        DBUtils.executeUpdate(createEnrollmentTable);
    }

    public void addEnrollment(Enrollment enrollment) {
        if (findEnrollmentByID(enrollment.getStudentId() ,enrollment.getCourseId()) == null){
            String addEnrollmentSql = "insert into enrollment (student_id, course_id, enrollment_date, grade) values  (?,?,?,?)";
            DBUtils.executeUpdate(addEnrollmentSql, enrollment.getStudentId(), enrollment.getCourseId(), enrollment.getEnrollmentDate(), enrollment.getGrade());
            return;
        }
        throw new RuntimeException("This student id and course id exist!");

    }

    public void UpdateEnrollment(Enrollment enrollment) {
        if (findEnrollmentByID(enrollment.getStudentId(), enrollment.getCourseId()) == null){
            throw new RuntimeException("This id does not exist!");
        }
        String updateEnrollmentSql = "update enrollment set enrollment_date = ?, grade = ? where student_id = ? and course_id = ?";
        DBUtils.executeUpdate(updateEnrollmentSql,enrollment.getEnrollmentDate(), enrollment.getGrade(), enrollment.getStudentId(), enrollment.getCourseId());
    }

    public void DeleteEnrollment(int studentId, int courseId) {
        if (findEnrollmentByID(studentId, courseId) == null){
            throw new RuntimeException("This id does not exist!");
        }
        String deleteEnrollmentSql = "delete from enrollment where student_id = ? and course_id = ?";
        DBUtils.executeUpdate(deleteEnrollmentSql, studentId, courseId);
    }

    public void insertSampleEnrollment () {
        List<Enrollment> enrollments = new ArrayList<>();
        enrollments.add(new Enrollment(1, 101, LocalDate.of(2025,7,20), 20));
        enrollments.add(new Enrollment(1, 103, LocalDate.of(2025,7,21), 17.75));
        enrollments.add(new Enrollment(2, 102, LocalDate.of(2025,7,22), 19.5));
        enrollments.add(new Enrollment(2, 105, LocalDate.of(2025,7,23), 18.25));
        enrollments.add(new Enrollment(3, 101, LocalDate.of(2025,7,24), 13));
        enrollments.add(new Enrollment(3, 104, LocalDate.of(2025,7,25), 19.75));
        enrollments.add(new Enrollment(4, 103, LocalDate.of(2025,7,26), 14.5));
        enrollments.add(new Enrollment(4, 105, LocalDate.of(2025,7,27), 12));
        enrollments.add(new Enrollment(5, 102, LocalDate.of(2025,7,28), 10));
        enrollments.add(new Enrollment(5, 104, LocalDate.of(2025,7,29), 16.5));
        enrollments.add(new Enrollment(6, 101, LocalDate.of(2025,7,30), 18));
        enrollments.add(new Enrollment(6, 105, LocalDate.of(2025,7,31), 19));
        enrollments.add(new Enrollment(7, 103, LocalDate.of(2025,8,1), 12.5));
        enrollments.add(new Enrollment(8, 102, LocalDate.of(2025,8,2), 14));
        enrollments.add(new Enrollment(9, 104, LocalDate.of(2025,8,3), 10));

        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();
        createTableEnrollment();
        for (Enrollment enrollment : enrollments) {
            enrollmentRepository.addEnrollment(enrollment);
        }
    }
}
