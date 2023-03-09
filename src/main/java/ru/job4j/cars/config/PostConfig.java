package ru.job4j.cars.config;

import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.File;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.service.CarBodyService;
import ru.job4j.cars.service.DriverService;
import ru.job4j.cars.service.EngineService;

public class PostConfig {
    public static PostDto postToDto(Post post, DriverService driverService, EngineService engineService) {
        var car = post.getCar();
        var userId = post.getUser().getId();
        var pictures = post.getPictures();
        return new PostDto(post.getId(), post.getDescription(), driverService.findByUserId(userId).get().getName(),
                post.getHistory(), pictures.stream().map(File::getId).toList(), post.getCreated(),
                post.getMark().getName(), car.getName(),
                post.isSold(), pictures.get(0).getId(), post.getPrice(),
                engineService.findById(car.getEngine().getId()).get().getName(), userId,
                post.getCarBody().getName());
    }
}
