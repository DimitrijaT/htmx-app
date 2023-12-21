package dt.project.java.web.rest;

import org.springframework.web.bind.annotation.RestController;

import dt.project.java.model.Student;
import dt.project.java.model.dto.StudentDto;
import dt.project.java.service.StudentService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/")
public class RestStudentController {

        private final StudentService studentService;

        public RestStudentController(StudentService studentService) {
                this.studentService = studentService;
        }

        @GetMapping("get-student")
        public List<Student> getStudent() {
                return studentService.getStudents();
        }

        @PostMapping("mouse_entered")
        public String postMethodName() {
                System.out.println("mouse_entered!!!");
                // System.out.println(data);
                return "index";
        }

}
