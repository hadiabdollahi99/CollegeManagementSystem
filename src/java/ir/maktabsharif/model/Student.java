package ir.maktabsharif.model;

public class Student {
    private int id;
    private String name;
    private String major;
    private int year;
    private double gpa;

    public Student(int id,String name,String major,int year, double gpa) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.year = year;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public int getYear() {
        return year;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", year=" + year +
                ", gpa=" + gpa +
                '}';
    }
}
