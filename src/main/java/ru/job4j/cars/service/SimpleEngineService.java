package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.repository.EngineRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SimpleEngineService implements EngineService {

    private EngineRepository engineRepository;

    @Override
    public Optional<Engine> findById(int id) {
        return engineRepository.findById(id);
    }

    @Override
    public List<Engine> findAll() {
        return engineRepository.findAll();
    }
}
