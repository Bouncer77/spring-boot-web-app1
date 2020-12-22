package com.bouncer77.springbootapp1.controller;

import com.bouncer77.springbootapp1.entity.Person;
import com.bouncer77.springbootapp1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Kosenkov Ivan
 * Created by Kosenkov Ivan on 15.09.2020
 */
@Controller
public class WebMainController {

    @Autowired
    private PersonService personService;

    @Value("${welcome.message}")
    private String message;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping(value = {"/", "/index"})
    public String index(Authentication authentication, Model model) {

        model.addAttribute("message", message);
        // вывести логин-ссылку на страницу профиля
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Person person = personService.read(userDetails.getUsername());
            if (Objects.nonNull(person)) {
                model.addAttribute("personId", person.getId());
            }
        }

        return "main";
    }

    @GetMapping("/about")
    public String showAbout(Authentication authentication) {
        return "/about";
    }

    @GetMapping("/contact")
    public String showContact() {
        return "/contact";
    }

    @GetMapping("/applications")
    public String showApplications() {
        return "/applications";
    }

    @GetMapping(value = "/profile")
    public String profilePerson(HttpServletRequest request, Model model) {

        // #httpServletRequest.remoteUser}
        Person person = personService.read(request.getRemoteUser());
        if (Objects.nonNull(person)) {
            model.addAttribute("person", person);
        } else {
            model.addAttribute("errorMsg", "Person did not found");
        }
        return "profile";
    }

    @PostMapping(value = "/profile")
    public String profilePerson(HttpServletRequest request,
                                Model model,
                                @RequestParam("avatar") MultipartFile avatar) throws IOException {

        if (avatar != null) {
            File uploadDir = new File(uploadPath);

            /*if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }*/

            String uuid = UUID.randomUUID().toString();
            final String resultAvatarName = uuid + "." + avatar.getOriginalFilename();

            avatar.transferTo(new File(uploadPath + "\\" + resultAvatarName));
        }

        return "redirect:" + "/profile";
    }
}
