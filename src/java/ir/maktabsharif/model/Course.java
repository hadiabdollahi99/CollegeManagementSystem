package ir.maktabsharif.model;

public class Course {
    private int id;
    private String title;
    private String department;
    private int credits;

    public Course(int id, String title, String department, int credits) {
        this.id = id;
        this.title = title;
        this.department = department;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDepartment() {
        return department;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", department='" + department + '\'' +
                ", credits=" + credits +
                '}';
    }
}
