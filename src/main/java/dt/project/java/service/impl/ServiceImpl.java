package dt.project.java.service.impl;

import java.util.List;

import dt.project.java.model.Student;
import dt.project.java.repository.StudentRepository;
import dt.project.java.service.StudentService;

import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements StudentService {

        private final StudentRepository studentRepository;

        public ServiceImpl(StudentRepository studentRepository) {
                this.studentRepository = studentRepository;
        }

        @Override
        public List<Student> getStudents() {
                return studentRepository.findAll();
        }

}
