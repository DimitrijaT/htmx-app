package dt.project.java.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/", "/main" })
public class MainController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/htmx-crud")
    public String getHtmx(Model model) {
        model.addAttribute("bodyContent", "htmx-index");
        return "master-template";
    }

}