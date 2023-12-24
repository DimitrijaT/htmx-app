package dt.project.java.web;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dt.project.java.model.Student;
import dt.project.java.service.StudentService;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;

@Controller
@RequestMapping({ "/", "/main" })
// @EnableWebMvc
public class InfiniteScrollingControllerFragments {

    private final StudentService studentService;

    public InfiniteScrollingControllerFragments(StudentService studentService) {
        this.studentService = studentService;
    }

    // Pagination
    @HxRequest
    @GetMapping("/student-page")
    public String pagableListStudents(
            Model model,
            @RequestParam("page") Optional<Integer> page) {

        Integer currentPage = page.orElse(1);
        Page<Student> studentPage = studentService.findPaginated(PageRequest.of(currentPage - 1, 5));
        model.addAttribute("studentPage", studentPage);

        if (studentPage.getContent().size() == currentPage) {
            currentPage = null;
        }

        model.addAttribute("currentPage", currentPage);

        return "htmx-fragments/student-page";
    }

}