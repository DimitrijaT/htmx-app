package dt.project.java.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import dt.project.java.service.StudentService;

@Controller
@RequestMapping("/main")
public class MainController {

    private final StudentService StudentService;

    public MainController(StudentService studentService) {
        StudentService = studentService;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/get-students")
    public String getStudents(Model model) {
        model.addAttribute("students", StudentService.getStudent());
        return "getstudents";
    }

}