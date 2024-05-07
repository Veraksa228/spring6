package hiber.controller;

import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class controller {

    UserService userService;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/new";

    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        userService.add(user);
        return "redirect:/users";
    }

    @PostMapping("/remove")
    public String removeUser(@RequestParam("userId") Long id, Model model) {
        System.out.println(id);
        User user = userService.findUser(id);
        System.out.println(user.toString());
        userService.removeUser(user);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("userId") Long userId, Model model) {
        User user = userService.findUser(userId);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {

        userService.updateUser(user);
        return "redirect:/users";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
