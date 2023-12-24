package dt.project.java.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dt.project.java.model.dto.StudentDto;
import dt.project.java.service.StudentService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping({ "/", "/main" })
public class MainControllerFragments {

    private final StudentService studentService;

    public MainControllerFragments(StudentService studentService) {
        this.studentService = studentService;
    }

    @HxRequest
    @GetMapping("/get-students")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getStudents());

        return "htmx-fragments/get-students";
    }

    @HxRequest
    @GetMapping("/get-student/{studentIndex}")
    public String getOneStudent(Model model, @PathVariable Long studentIndex) {
        model.addAttribute("student", studentService.getStudent(studentIndex));
        return "htmx-fragments/student-item";
    }

    @HxRequest
    @GetMapping("/edit/student/{studentIndex}")
    public String getEditStudentPage(Model model, @PathVariable(required = false) Long studentIndex) {
        if (studentIndex == null) {
            return "htmx-fragments/edit-student";
        }
        model.addAttribute("student", studentService.getStudent(studentIndex));
        return "htmx-fragments/edit-student";
    }

    @HxRequest
    @PostMapping("/edit/student")
    public String editExistingStudent(
            Model model,
            HttpServletResponse response,
            @RequestParam(value = "id") Long studentIndex,
            @RequestParam(value = "name") String studentName,
            @RequestParam(value = "age") String studentSurname) {

        StudentDto studentDto = new StudentDto(studentIndex, studentName, studentSurname);

        studentService.updateStudent(studentDto);

        model.addAttribute("student", studentService.getStudent(studentIndex));

        response.addHeader("HX-Trigger", "editedUser");
        return getOneStudent(model, studentIndex);

    }


    @HxRequest
    @GetMapping("/add/student")
    public String getAddStudentPage(Model model) {
        return "htmx-fragments/add-student";
    }


    @HxRequest
    @PostMapping("/add/student")
    public void addNewStudent(
            Model model,
            HttpServletResponse response,
            @RequestParam(value = "name", required = false) String studentName,
            @RequestParam(value = "age", required = false) String studentSurname) {

        if (studentName != null && studentSurname != null && studentName != "" && studentSurname != "") {

            studentService.addStudent(studentName, studentSurname);

            response.addHeader("HX-Trigger", "editedUser");
        }
    }

    @HxRequest
    @DeleteMapping("/delete/student/{id}")
    public void postMethodName(HttpServletResponse response,
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        response.addHeader("HX-Trigger", "editedUser");
    }

}