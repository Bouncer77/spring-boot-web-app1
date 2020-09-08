package com.bouncer77.springbootapp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 08.09.2020
 */

@Controller
@RequestMapping("/thymeleaf")
public class SyntaxExampleController {

    @GetMapping(value = "/")
    public String getEx0(Model model) {

        return "thymeleaf_example/ex_main";
    }

    @GetMapping(value = "/ex1")
    public String getEx1(Model model) {
        model.addAttribute("defaultname", "Peter Parker");
        model.addAttribute("greetings", "Hello User");
        //model.addAttribute("book", getTestBook());

        return "thymeleaf_example/ex1";
    }

    @GetMapping(value = "/ex2")
    public String getEx2(Model model) {

        return "thymeleaf_example/ex2";
    }
}
