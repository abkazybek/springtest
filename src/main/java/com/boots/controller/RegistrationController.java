package com.boots.controller;

import com.boots.entity.FileUploadUtil;
import com.boots.entity.User;
import com.boots.repository.UserRepository;
import com.boots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }

    @Autowired
    private UserRepository repo;

    @PostMapping("/registration/save")
    public RedirectView saveUser(User user,
                                 @RequestParam("image") MultipartFile multipartFile) throws IOException, IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        user.setPhotos(fileName);

        User savedUser = repo.save(user);

        String uploadDir = "user-photos/" + savedUser.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/users", true);
    }
}
