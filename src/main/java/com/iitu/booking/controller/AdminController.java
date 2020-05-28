package com.iitu.booking.controller;

import com.iitu.booking.model.Field;
import com.iitu.booking.model.FieldType;
import com.iitu.booking.model.UserAccount;
import com.iitu.booking.service.FieldService;
import com.iitu.booking.service.FieldTypeService;
import com.iitu.booking.service.UserAccountService;
import com.iitu.booking.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserAccountService userService;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private FieldTypeService fieldTypeService;

    @GetMapping("")
    public String showHomePage(Authentication authentication, Model model) {
        if (authentication.getName()!= null)
            model.addAttribute("authenticatedUser", authentication.getName());
        model.addAttribute("users", userService.getAll());
        return "admin/users";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new UserAccount());
        return "admin/register";
    }

    @PostMapping("/register")
    public String register(UserAccount user) {
        userService.registerUser(user);
        return "admin/login";
    }

    @GetMapping("/profile/{username}")
    public String showProfilePage(Authentication authentication, @PathVariable String username, Model model) {
        if (authentication.getName()!= null)
            model.addAttribute("authenticatedUser", authentication.getName());
        model.addAttribute("user", userService.getUserByUsername(username));
        return "admin/profile";
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
        return "redirect:/users";
    }

    @PostMapping("user/delete/{id}")
    public String deleteUser(@PathVariable("id") UserAccount user){
        userService.delete(user);
        return "redirect:/users";
    }

    @GetMapping("fields")
    public String showFieldsPage(Authentication authentication, Model model) {
        if (authentication.getName()!= null)
            model.addAttribute("authenticatedUser", authentication.getName());
        model.addAttribute("fields", fieldService.getAll());
        return "admin/fields";
    }

    @GetMapping("field/add")
    public String showAddFieldPage(Authentication authentication, Model model) {
        if(authentication.getName() != null) {
            model.addAttribute("authenticatedUser", authentication.getName());
        }
        model.addAttribute("field", new Field());
        model.addAttribute("fieldTypes", fieldTypeService.getAllFieldTypes());
        return "admin/addField";
    }

    @PostMapping("field/add")
    public String addField(Authentication authentication, Field field, Model model) {
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

        fieldService.addField(field, authentication);
//        model.addAttribute("userRegistered", "User is successfully registered");
//        }
        return "redirect:/field/add";
    }

    @PostMapping("field/delete/{id}")
    public String deleteField(@PathVariable("id") Field field){
        fieldService.delete(field);
        return "redirect:/fields";
    }

    @GetMapping("fieldTypes")
    public String showFieldTypesPage(Authentication authentication, Model model) {
        if (authentication.getName()!= null)
            model.addAttribute("authenticatedUser", authentication.getName());
        model.addAttribute("fieldTypes", fieldTypeService.getAllFieldTypes());
        return "admin/fieldTypes";
    }

    @GetMapping("fieldType/add")
    public String showAddFieldTypePage(Authentication authentication, Model model) {
        if(authentication.getName() != null) {
            model.addAttribute("authenticatedUser", authentication.getName());
        }
        model.addAttribute("fieldType", new FieldType());
        model.addAttribute("fieldTypes", fieldTypeService.getAllFieldTypes());
        return "admin/addFieldType";
    }

    @PostMapping("fieldType/add")
    public String addFieldType(Authentication authentication, FieldType fieldType, Model model) {
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

        fieldTypeService.addFieldType(fieldType);
//        model.addAttribute("userRegistered", "User is successfully registered");
//        }
        return "redirect:/fieldType/add";
    }

    @PostMapping("fieldType/delete/{id}")
    public String deleteFieldType(@PathVariable("id") FieldType fieldType){
        fieldTypeService.delete(fieldType);
        return "redirect:/fieldTypes";
    }


}

