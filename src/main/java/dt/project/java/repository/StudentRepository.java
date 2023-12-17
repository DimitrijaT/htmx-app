package dt.project.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dt.project.java.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
