package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.repository.DriverRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SimpleDriverService implements DriverService {

    private DriverRepository driverRepository;

    @Override
    public Driver create(Driver driver) {
        return driverRepository.create(driver);
    }

    @Override
    public Optional<Driver> findById(int id) {
        return driverRepository.findById(id);
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public Optional<Driver> findByUserId(int userId) {
        return driverRepository.findByUserId(userId);
    }
}
