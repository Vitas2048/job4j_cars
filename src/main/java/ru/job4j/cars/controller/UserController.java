package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.DriverService;
import ru.job4j.cars.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final DriverService driverService;

    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("name", "");
        return "users/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, @RequestParam String name, Model model) {
        var driver = new Driver();
        driver.setName(name);
        driver.setUser(user);
        driverService.create(driver);
        if (userService.create(user).isEmpty()) {
            model.addAttribute("message", "Данный логин уже используется");
            return "errors/error";
        }
        return "users/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "users/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpServletRequest request) {
        try {
            var userOptional = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
            if (userOptional.isEmpty()) {
                model.addAttribute("message", "Неправильно введен логин или пароль");
                return "errors/error";
            }
            var session = request.getSession();
            session.setAttribute("user", userOptional.get());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "redirect:/posts/list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/posts/list";
    }
}
