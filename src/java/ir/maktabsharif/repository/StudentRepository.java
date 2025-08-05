package ir.maktabsharif.repository;

import ir.maktabsharif.utils.DBUtils;
import ir.maktabsharif.model.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public List<Student> findAllStudents () {
        List<Student> students = new ArrayList<>();
        String sql = "select * from student";
        try (
                Statement stmt = DBUtils.getConnection().createStatement();
                ResultSet result = stmt.executeQuery(sql))
        {
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String major = result.getString(3);
                int year = result.getInt(4);
                double gpa = result.getDouble(5);
                students.add(new Student(id, name, major, year, gpa));
            }
            return students;
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to find students", exception);
        }
    }

    public Student findStudentByID (int id) {
        String sql = "select * from student where id = ?";
        try (
                PreparedStatement prepared = DBUtils.getConnection().prepareStatement(sql))
        {
            prepared.setInt(1, id);
            ResultSet result = prepared.executeQuery();
            if (result.next()) {
                String name = result.getString(2);
                String major = result.getString(3);
                int year = result.getInt(4);
                double gpa = result.getDouble(5);
                return new Student(id, name, major, year, gpa);
            }
            return null;
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to find student by this id", exception);
        }
    }


    public void createTableStudent() {
        String createStudentTable = "create table student (\n" +
                "    id int primary key,\n" +
                "    name varchar,\n" +
                "    major varchar,\n" +
                "    year int,\n" +
                "    gpa decimal\n" +
                ")";
        DBUtils.executeUpdate(createStudentTable);
    }

    public void addStudent(Student student) {
        if (findStudentByID(student.getId()) == null){
            String addStudentSql = "insert into student (id, name, major, year, gpa) values (?,?,?,?,?)";
            DBUtils.executeUpdate(addStudentSql, student.getId(), student.getName(), student.getMajor(), student.getYear(), student.getGpa());
            return;
        }
        throw new RuntimeException("This id: " + student.getId() + " exist!");
    }

    public void updateStudent(Student student) {
        if (findStudentByID(student.getId()) == null){
            throw new RuntimeException("This id does not exist!");
        }
        String updateStudentSql = "update student set name = ?, major = ?, year = ?, gpa = ? where id = ?";
        DBUtils.executeUpdate(updateStudentSql, student.getName(), student.getMajor(), student.getYear(), student.getGpa(), student.getId());
    }

    public void deleteStudent(int id) {
        if (findStudentByID(id) == null){
            throw new RuntimeException("This id does not exist!");
        }
        String deleteStudentSql = "delete from student where id = ?";
        DBUtils.executeUpdate(deleteStudentSql, id);
    }

    public void insertSampleStudents () {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John Smith", "Computer Science", 3, 3.75));
        students.add(new Student(2, "Emily Johnson", "Electrical Engineering", 2, 3.92));
        students.add(new Student(3, "Michael Brown", "Mathematics", 4, 3.42));
        students.add(new Student(4, "Sarah Davis", "Physics", 1, 3.95));
        students.add(new Student(5, "David Wilson", "Mechanical Engineering", 3, 3.15));
        students.add(new Student(6, "Jessica Miller", "Computer Science", 2, 3.78));
        students.add(new Student(7, "Robert Taylor", "Industrial Engineering", 4, 3.28));
        students.add(new Student(8, "Jennifer Anderson", "Chemistry", 2, 3.85));
        students.add(new Student(9, "Williams Thomas", "Civil Engineering", 3, 3.50));
        students.add(new Student(10, "Elizabeth Jackson", "Biomedical Engineering", 4, 3.88));

        StudentRepository studentRepository = new StudentRepository();
        createTableStudent();
        for (Student student : students) {
            studentRepository.addStudent(student);
        }
    }
}
