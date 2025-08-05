package ir.maktabsharif.repository;

import ir.maktabsharif.utils.DBUtils;
import ir.maktabsharif.model.Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    public List<Course> findAllCourses () {
        List<Course> courses = new ArrayList<>();
        String sql = "select * from course";
        try (
                Statement stmt = DBUtils.getConnection().createStatement();
                ResultSet result = stmt.executeQuery(sql))
        {
            while (result.next()) {
                int id = result.getInt(1);
                String title = result.getString(2);
                String department = result.getString(3);
                int credits = result.getInt(4);
                courses.add(new Course(id, title, department, credits));
            }
            return courses;
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to find students", exception);
        }
    }

    public Course findCourseByID (int id) {
        String sql = "select * from course where id = ?";
        try (
                PreparedStatement prepared = DBUtils.getConnection().prepareStatement(sql))
        {
            prepared.setInt(1, id);
            ResultSet result = prepared.executeQuery();
            if (result.next()) {
                String title = result.getString(2);
                String department = result.getString(3);
                int credits = result.getInt(4);
                return new Course(id, title, department, credits);
            }
            return null;
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to find student by this id", exception);
        }
    }

    public void createTableCourse() {
        String createCourseTable = "create table course (\n" +
                "    id int primary key,\n" +
                "    title varchar,\n" +
                "    department varchar,\n" +
                "    credits int\n" +
                ")";
        DBUtils.executeUpdate(createCourseTable);
    }

    public void addCourse(Course course) {
        if (findCourseByID(course.getId()) == null){
            String addCourseSql = "insert into course (id, title, department, credits) values (?,?,?,?)";
            DBUtils.executeUpdate(addCourseSql, course.getId(), course.getTitle(), course.getDepartment(), course.getCredits());
            return;
        }
        throw new RuntimeException("This id: " + course.getId() + " exist!");
    }

    public void updateCourse(Course course) {
        if (findCourseByID(course.getId()) == null){
            throw new RuntimeException("This id does not exist!");
        }
        String updateCourseSql = "update course set title = ?, department = ?, credits = ? where id = ?";
        DBUtils.executeUpdate(updateCourseSql, course.getTitle(), course.getDepartment(), course.getCredits(), course.getId());
    }

    public void deleteCourse(int id) {
        if (findCourseByID(id) == null){
            throw new RuntimeException("This id does not exist!");
        }
        String deleteCourseSql = "delete from course where id = ?";
        DBUtils.executeUpdate(deleteCourseSql, id);
    }

    public void insertSampleCourses () {
        List<Course> courses = new ArrayList<>(List.of(
                new Course(101, "Advanced Programming", "Computer Science", 3),
                new Course(102, "Electric Circuits", "Electrical Engineering", 4),
                new Course(103, "Discrete Mathematics", "Mathematics", 3),
                new Course(104, "Modern Physics", "Physics", 4),
                new Course(105, "Probability and Statistics", "Mathematics", 3)));

        CourseRepository courseRepository = new CourseRepository();
        createTableCourse();
        for (Course course : courses) {
            courseRepository.addCourse(course);
        }
    }
}
