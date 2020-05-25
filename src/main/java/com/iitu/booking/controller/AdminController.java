package com.iitu.booking.controller;

import com.iitu.booking.model.UserAccount;
import com.iitu.booking.model.UserRole;
import com.iitu.booking.service.UserAccountService;
import com.iitu.booking.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/")
public class AdminController {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserAccountService userService;

    @GetMapping("")
    public String showHomePage(Authentication authentication, Model model) {
        if (authentication.getName()!= null)
            model.addAttribute("authenticatedUser", authentication.getName());
        return "admin/home";
    }

    @GetMapping("users")
    public String showUsersPage(Authentication authentication, Model model) {
        if (authentication.getName()!= null)
            model.addAttribute("authenticatedUser", authentication.getName());
        model.addAttribute("users", userService.getAll());
        return "admin/users";
    }

    @GetMapping("user/add")
    public String showAddUserPage(Authentication authentication, Model model) {
        if(authentication.getName() != null) {
            model.addAttribute("authenticatedUser", authentication.getName());
        }
        model.addAttribute("user", new UserAccount());
        model.addAttribute("roles", userRoleService.getRolesMap());
        return "admin/addUser";
    }

    @PostMapping("user/add")
    public String addUser(Authentication authentication, UserAccount user, Model model) {
        if(authentication.getName() != null) {
            model.addAttribute("authenticatedUser", authentication.getName());
        }
//        UserAccount userExists = userService.findUser(user);
//        if (userExists != null) {
//            model.addAttribute("userExists", true);
//        }
//        else if (bindingResult.hasErrors()) {
//            model.addAttribute("bindingResult", bindingResult);
//        }
//        else {
            userService.addUser(user);
            model.addAttribute("userRegistered", "User is successfully registered");
//        }
        return "redirect:/user/add";
    }

    @PostMapping("user/delete/{id}")
    public String deleteUser(@PathVariable("id") UserAccount user){
        userService.delete(user);
        return "redirect:/users";
    }
}

