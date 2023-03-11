package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.config.PostConfig;
import ru.job4j.cars.dto.FileDto;
import ru.job4j.cars.model.*;
import ru.job4j.cars.service.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    private final CarService carService;

    private final DriverService driverService;

    private final EngineService engineService;

    private final MarkService markService;

    private final CarBodyService carBodyService;

    @GetMapping("/list")

    public String getAll(Model model) {
        try {
            var posts = postService.findAll().stream().map(p -> {
                return PostConfig.postToDto(p, driverService, engineService);
            }).toList();
            model.addAttribute("posts", new HashSet<>(posts));
        } catch (Exception e) {
            return "redirect:/posts/create";
        }
        model.addAttribute("carBodies", carBodyService.findAll());
        model.addAttribute("marks", markService.findAll());
        return "posts/list";
    }

    @GetMapping("/look/{id}")
    public String getDescribePage(@PathVariable int id, Model model) {
        var post = postService.findById(id).get();
        var postDto = PostConfig.postToDto(post, driverService, engineService);
        model.addAttribute("post", postDto);
        return "posts/look";
    }
    @GetMapping("/create")
    public String getCreatedPage(Model model, @SessionAttribute User user) {
        model.addAttribute("carBodies", carBodyService.findAll());
        model.addAttribute("marks", markService.findAll());
        model.addAttribute("engines", engineService.findAll());
        return "posts/create";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute Post post,
                         @SessionAttribute User user, @RequestParam MultipartFile file,
                         @RequestParam int autoMark, @RequestParam int carEngine, @RequestParam int body,
                         @RequestParam String carName) {
        var driver = driverService.findByUserId(user.getId()).get();
        var car = new Car();
        var mark = markService.findById(autoMark).get();
        var engine = engineService.findById(carEngine).get();
        var carBody = carBodyService.findById(body).get();
        System.out.println(car.getId());
        car.setName(carName);
        car.setEngine(engine);
        car.setMark(mark);
        car.setCarBody(carBody);
        post.setMark(car.getMark());
        post.setUser(user);
        post.setCar(car);
        post.getParticipates().add(user);
        post.setCarBody(carBody);
        carService.save(car);
        car.getDrivers().add(driver);
        try {
            postService.save(post, new FileDto(file.getOriginalFilename(), file.getBytes()));
            return "posts/list";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
    }

    @PostMapping("filter")
    public String filterBy(Model model, @RequestParam int markId, @RequestParam int carBodyId) {
        model.addAttribute("carBodies", carBodyService.findAll());
        model.addAttribute("marks", markService.findAll());
        Set<Post> posts = new HashSet<>();
        if (markId == 0 && carBodyId == 0) {
            posts = new HashSet<>(postService.findAll());
        }
        if (markId != 0 && carBodyId == 0) {
            posts = new HashSet<>(postService.findByMark(markId));
        }
        if (markId == 0 && carBodyId != 0) {
            posts = new HashSet<>(postService.findByCarBodyId(carBodyId));
        }
        if (markId != 0 && carBodyId != 0) {
            posts = new HashSet<>(postService.findByMarkAndCarBody(markId, carBodyId));
        }
        model.addAttribute("posts", posts.stream().map(p -> {
            return PostConfig.postToDto(p, driverService, engineService);
        }).toList());
        return "posts/list";
    }

    @GetMapping("/setSold/{id}")
    public String setSold(@PathVariable int id, Model model) {
        var post = postService.findById(id).get();
        post.setSold(true);
        postService.update(post);
        return "redirect:/posts/look/{id}";
    }

    @PostMapping("/addPhoto/{id}")
    public String addPhoto(@PathVariable int id, Model model, @RequestParam MultipartFile file) {
        var post = postService.findById(id).get();
        try {
            postService.updateWithFile(post, new FileDto(file.getOriginalFilename(), file.getBytes()));
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "errors/404";
        }
        return "redirect:/posts/look/{id}";
    }
}
