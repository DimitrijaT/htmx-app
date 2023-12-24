package dt.project.java.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dt.project.java.model.Student;
import dt.project.java.model.dto.StudentDto;

public interface StudentService {
        public List<Student> getStudents();

        public Student getStudent(Long index);

        public List<Student> findStudents(String queryParam);

        public boolean updateStudent(StudentDto studentDto);

        public boolean addStudent(String studentName, String studentSurname);

        public boolean deleteStudent(Long studentIndex);

        public Page<Student> findPaginated(Pageable pageable);

}
