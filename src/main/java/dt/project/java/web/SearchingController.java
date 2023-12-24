package dt.project.java.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import dt.project.java.model.Student;
import dt.project.java.service.StudentService;

@Controller
@RequestMapping({ "/", "/main" })
public class SearchingController {

    private final StudentService studentService;

    public SearchingController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/htmx-search")
    public String getHtmx(Model model) {
        model.addAttribute("bodyContent", "htmx-search");
        return "master-template";
    }

    @PostMapping("/search-student")
    public String postMethodName(Model model, @RequestBody String query) {

        ArrayList<Student> studentList = new ArrayList<Student>();

        if (query != null &&
                !query.equals("search=")) {
            query = query.split("=")[1];
            query = query.replace("+", " ");
            studentList.addAll(studentService.findStudents(query));
            model.addAttribute("students", studentList);
        }

        return "htmx-fragments/get-students";
    }

}