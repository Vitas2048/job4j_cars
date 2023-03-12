package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.config.PostConfig;
import ru.job4j.cars.dto.FileDto;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.Mark;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SimplePostService implements PostService {

    private PostRepository postRepository;

    private FileService fileService;

    private DriverService driverService;

    private EngineService engineService;

    @Override
    public List<Post> findByLastDay() {
        return postRepository.findByLastDay();
    }

    @Override
    public List<Post> findWithImg() {
        return postRepository.findWithImg();
    }

    @Override
    public List<Post> findByMark(int markId) {
        return postRepository.findByMark(markId);
    }

    @Override
    public Post create(Post post) {
        return postRepository.create(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post save(Post post, FileDto fileDto) {
        var file = fileService.save(fileDto);
        post.getPictures().add(file);
        return postRepository.create(post);
    }

    @Override
    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    @Override
    public void update(Post post) {
        postRepository.update(post);
    }

    @Override
    public void updateWithFile(Post post, FileDto fileDto) {
        var file = fileService.save(fileDto);
        post.getPictures().add(file);
        postRepository.update(post);
    }

    @Override
    public List<Post> findByMarkAndCarBody(int markId, int carBodyId) {
        return postRepository.findByMarkAndCarBody(markId, carBodyId);
    }

    @Override
    public List<Post> findByCarBodyId(int carBodyId) {
        return postRepository.findByCarBodyId(carBodyId);
    }

    @Override
    public Set<PostDto> toDtoSet(List<Post> posts) {
        return postRepository.findAll().stream().map(p -> {
            return PostConfig.postToDto(p, driverService, engineService);
        }).collect(Collectors.toSet());
    }
}
