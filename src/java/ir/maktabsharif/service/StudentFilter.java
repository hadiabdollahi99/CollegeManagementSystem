package ir.maktabsharif.service;

import ir.maktabsharif.model.Student;

@FunctionalInterface
public interface StudentFilter {
    boolean matches(Student student);
}
