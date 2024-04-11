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
        String countryCode = currentlocale.getCountry();
        String countryName = currentlocale.getDisplayName();

        String langCode = currentlocale.getLanguage();
        String langName = currentlocale.getDisplayLanguage();

        System.out.println(countryCode + ": " + countryName);
        System.out.println(langCode + ": " + langName);

        System.out.println("===============");
        String[] languages = Locale.getISOLanguages();

        for(String language : languages) {
            Locale locale = new Locale(language);
            System.out.println(language + ": " + locale.getDisplayLanguage());
        }

        return "redirect:/book/list";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
