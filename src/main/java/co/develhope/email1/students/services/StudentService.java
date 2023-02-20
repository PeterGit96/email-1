package co.develhope.email1.students.services;

import org.springframework.stereotype.Service;
import co.develhope.email1.students.entities.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private static List<Student> students = Arrays.asList(
            new Student("1", "Pietro", "Gallina", "ptradventure@hotmail.it"),
            new Student("2", "Walter", "White", "walter@white.com"),
            new Student("3", "Samuel", "Dungeon", "samuel@dangeon.com"),
            new Student("4", "John", "Walker", "john@walker.com")
    );

    public Student getStudentById(String studentId) {
        Optional<Student> studentFromList = students.stream().filter(student -> student.getId().equals(studentId)).findFirst();
        return studentFromList.isPresent() ? studentFromList.get() : null;
    }

}
