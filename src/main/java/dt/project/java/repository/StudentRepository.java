package dt.project.java.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dt.project.java.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

        Optional<Student> findById(Long id);

        List<Student> findByNameContainingIgnoreCase(String name);

}
