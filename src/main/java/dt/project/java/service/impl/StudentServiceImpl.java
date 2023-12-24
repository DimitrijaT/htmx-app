package dt.project.java.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dt.project.java.model.Student;
import dt.project.java.model.dto.StudentDto;
import dt.project.java.repository.StudentRepository;
import dt.project.java.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

        private final StudentRepository studentRepository;

        public StudentServiceImpl(StudentRepository studentRepository) {
                this.studentRepository = studentRepository;
        }

        @Override
        public List<Student> getStudents() {
                return studentRepository.findAll();
        }

        @Override
        public Student getStudent(Long index) {
                return studentRepository.findById(index).orElseThrow();
        }

        @Override
        public boolean updateStudent(StudentDto studentDto) {
                Student student = studentRepository.findById(studentDto.id).orElseThrow();
                student.setName(studentDto.name);
                student.setAge(studentDto.age);
                try {
                        studentRepository.save(student);
                        return true;
                } catch (Exception e) {
                        return false;
                }
        }

        @Override
        public boolean addStudent(String studentName, String studentSurname) {
                Student student = new Student(studentName, studentSurname);
                studentRepository.save(student);
                return true;
        }

        @Override
        public boolean deleteStudent(Long studentIndex) {
                try {
                        studentRepository.deleteById(studentIndex);
                        return true;
                } catch (Exception e) {
                        return false;
                }
        }

        @Override
        public Page<Student> findPaginated(Pageable pageable) {

                int pageSize = pageable.getPageSize();
                int currentPage = pageable.getPageNumber();
                int startItem = currentPage * pageSize;
                List<Student> list;

                if (studentRepository.findAll().size() < startItem) {
                        list = List.of();
                } else {
                        int toIndex = Math.min(startItem + pageSize, studentRepository.findAll().size());
                        list = studentRepository.findAll().subList(startItem, toIndex);
                }

                Page<Student> bookPage = new PageImpl<Student>(list, PageRequest.of(currentPage, pageSize),
                                studentRepository.findAll().size());

                return bookPage;

        }

        @Override
        public List<Student> findStudents(String queryParam) {
                List<Student> students = studentRepository.findByNameContainingIgnoreCase(queryParam);
                return students;
        }

}
