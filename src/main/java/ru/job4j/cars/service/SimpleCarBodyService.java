package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarBody;
import ru.job4j.cars.repository.CarBodyRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleCarBodyService implements CarBodyService {

    CarBodyRepository carBodyRepository;

    @Override
    public Optional<CarBody> findById(int id) {
        return carBodyRepository.findById(id);
    }

    @Override
    public List<CarBody> findAll() {
        return carBodyRepository.findAll();
    }
}
