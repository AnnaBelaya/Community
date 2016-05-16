package ua.belaya.community.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.belaya.community.domain.Student;
import ua.belaya.community.repository.StudentJpaRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Anna Belaya
 */

@Controller
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private Environment environment;

    @Autowired
    private StudentJpaRepository studentRepository;

    @ModelAttribute()
    private Student emptyStudent() {
        return new Student();
    }

    @Value("default.enrolment.key")
    private String key;

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/enrol",method = RequestMethod.POST)
    public ModelAndView enrol(@RequestParam("key") String receivedKey) {
        ModelAndView modelAndView = new ModelAndView();
        if (receivedKey.equals(environment.getProperty(key))) {
            modelAndView.setViewName("login");
        } else {
            modelAndView.setViewName("enrolmentfail");
        }
        return modelAndView;
    }

    @RequestMapping("/registration")
    public ModelAndView register() {
        return new ModelAndView("registration");
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerProcess(@Valid @ModelAttribute("student") Student student,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("registration");
        }

        studentRepository.save(student);

        return new ModelAndView("login");
    }
}
