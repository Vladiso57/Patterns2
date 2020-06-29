package com.Labs.Patterns.controller;

import com.Labs.Patterns.dto.User;
import com.Labs.Patterns.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RegistrationController  {

    @Autowired
    IUserService iUserService;

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("user") User user)
    {
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String doRegistration(@ModelAttribute("user") User user, Model model)
    {
        if(iUserService.isUserExist(user.getNickname()))
        {
            model.addAttribute("userExist",true);
            return "registrationPage";
        }
        iUserService.registerUser(user);
        return "redirect:/login";
    }
}
