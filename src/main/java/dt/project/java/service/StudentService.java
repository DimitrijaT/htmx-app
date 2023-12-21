package dt.project.java.service;

import java.util.List;

import dt.project.java.model.Student;
import dt.project.java.model.dto.StudentDto;

public interface StudentService {
        public List<Student> getStudents();

        public Student getStudent(Long index);

        public boolean updateStudent(StudentDto studentDto);

        public boolean addStudent(String studentName, String studentSurname);

        public boolean deleteStudent(Long studentIndex);

}
