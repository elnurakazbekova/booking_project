package com.iitu.booking.controller;

import com.iitu.booking.model.UserAccount;
import com.iitu.booking.model.UserRole;
import com.iitu.booking.service.UserAccountService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserAccountService userService;

    public AdminController(UserAccountService userService) {
        this.userService = userService;
    }

    @GetMapping("/addAdmin")
    public String admin(Authentication authentication, Model model){
        model.addAttribute("user", new UserAccount());
        if(authentication.getName()!=null) {
            model.addAttribute("remoteUser", authentication.getName());
            model.addAttribute("isAdmin", authentication.getAuthorities().contains(UserRole.ADMIN));
        }
        return "admin/addUser";
    }

    @PostMapping("/addAdmin")
    public String addUser(Authentication authentication, @Valid UserAccount user, BindingResult bindingResult, Model model){
        if(authentication.getName()!=null) {
            model.addAttribute("remoteUser", authentication.getName());
            model.addAttribute("isAdmin", authentication.getAuthorities().contains(UserRole.ADMIN));
        }
        UserAccount userExists = userService.findUser(user);
        if(userExists!=null) {
            model.addAttribute("userExists", true);
        }
        else if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
        }
        else{
            userService.addUser(user);
            model.addAttribute("userRegistered", "User is successfully registered");
        }

        return "admin/addUser";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") UserAccount user){
        userService.delete(user);
        return "redirect:/admin/addUser";
    }

    @GetMapping("/showUsers")
    public String showUsers(Model model, Authentication authentication){
        if(authentication.getName()!=null) {
            model.addAttribute("remoteUser", authentication.getName());
            model.addAttribute("isAdmin", authentication.getAuthorities().contains(UserRole.ADMIN));
        }
        model.addAttribute("users",userService.getAll());
        return "admin/showUsers";
    }
}

