package com.project.student_management_CRUD_api.student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service    //communicates with Autowired
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired //take care of automatic dependency injections
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> exists = studentRepository.findStudentByEmail(student.getEmail());
        if (exists.isPresent()) {
            throw new IllegalStateException("Student already exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("Student with id "+studentId+ " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("Student not found"));

        if(name != null && !name.isEmpty() && !Objects.equals(name, student.getName())) {
            student.setName(name);
        }
        if(email != null && !email.isEmpty() && !Objects.equals(email, student.getEmail())) {
            Optional<Student> exists = studentRepository.findStudentByEmail(email);
            if (exists.isPresent()) {
                throw new IllegalStateException("The email is taken");
            }
            student.setEmail(email);
        }
    }

}
