package dt.project.java.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dt.project.java.model.Student;
import dt.project.java.service.StudentService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;

@Controller
@RequestMapping({ "/", "/main" })
public class ContentController {

    private final StudentService studentService;

    public ContentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/htmx-content")
    public String getHtmx(Model model) {
        model.addAttribute("bodyContent", "htmx-content");
        return "master-template";
    }

    @HxRequest
    @GetMapping("/htmx-content-second")
    public String getHtmxSecond(Model model) {
        return "htmx-fragments/second-slide-content";
    }

    @HxRequest
    @GetMapping("/htmx-content-first")
    public String getHtmxFirst(Model model) {
        return "htmx-fragments/first-slide-content";
    }

    @HxRequest
    @GetMapping("/confirmed")
    public String getHtmxThird(Model model) {

        model.addAttribute("bodyContent", "htmx-infinite-scroll-main-page");

        Page<Student> studentPage = studentService.findPaginated(PageRequest.of(0, 5));
        model.addAttribute("studentPage", studentPage);

        return "master-template";
    }

}
