package dt.project.java.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dt.project.java.model.Student;
import dt.project.java.service.StudentService;

@Controller
@RequestMapping({ "/", "/main" })
public class InfiniteScrollingController {

    private final StudentService studentService;

    public InfiniteScrollingController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Infinite Scroll Main Page
    @GetMapping("/infinite-scroll-main-page")
    public String getMethodName(Model model) {

        model.addAttribute("bodyContent", "htmx-infinite-scroll-main-page");

        Page<Student> studentPage = studentService.findPaginated(PageRequest.of(0, 5));
        model.addAttribute("studentPage", studentPage);

        return "master-template";
    }

}