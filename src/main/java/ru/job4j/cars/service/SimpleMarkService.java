package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Mark;
import ru.job4j.cars.repository.MarkRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SimpleMarkService implements MarkService {

    private MarkRepository markRepository;
    @Override
    public List<Mark> findAll() {
        return markRepository.findAll();
    }

    @Override
    public Optional<Mark> findById(int id) {
        return markRepository.findById(id);
    }
}
