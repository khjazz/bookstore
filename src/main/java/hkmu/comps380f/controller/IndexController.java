package hkmu.comps380f.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(HttpServletRequest request) {

        Locale currentlocale = request.getLocale();

        return "redirect:/book/list";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
