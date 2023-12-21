package dt.project.java.web;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dt.project.java.model.dto.StudentDto;
import dt.project.java.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
// @EnableWebMvc
public class MainController {

    private final StudentService studentService;

    public MainController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/htmx")
    public String getHtmx(Model model) {
        model.addAttribute("bodyContent", "htmx-index");
        return "master-template";
    }

    // HTMX Handling

    @GetMapping("/get-students")
    public String getStudents(Model model) {
        model.addAttribute("students", studentService.getStudents());

        return "htmx-fragments/get-students";
    }

    @GetMapping("/get-student/{studentIndex}")
    public String getStudent(Model model, @PathVariable Long studentIndex) {
        model.addAttribute("student", studentService.getStudent(studentIndex));
        return "htmx-fragments/student-item";
    }

    @PostMapping("/mouse_entered")
    public String postMethodName(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "htmx-fragments/get-students";
    }

    @GetMapping("/edit/student/{studentIndex}")
    public String getEditStudent(Model model, @PathVariable Long studentIndex) {
        model.addAttribute("student", studentService.getStudent(studentIndex));
        return "htmx-fragments/edit-student";
    }

    // @RequestBody StudentDto studentDto
    @PostMapping("/edit/student")
    public String putMethodName(
            Model model,
            HttpServletResponse response,
            @RequestParam(value = "id", required = false) Long studentIndex,
            @RequestParam(value = "name", required = false) String studentName,
            @RequestParam(value = "age", required = false) String studentSurname) {

        System.out.println("studentIndex: " + studentIndex);
        System.out.println("studentName: " + studentName);
        System.out.println("studentSurname: " + studentSurname);

        StudentDto studentDto = new StudentDto(studentIndex, studentName, studentSurname);

        studentService.updateStudent(studentDto);

        model.addAttribute("student", studentService.getStudent(studentIndex));

        response.addHeader("HX-Trigger", "editedUser");

        return getStudent(model, studentIndex);

    }

}