package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.PriceHistory;
import ru.job4j.cars.repository.PriceHistoryRepository;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class SimplePriceHistoryService implements PriceHistoryService {

    private PriceHistoryRepository priceHistoryRepository;

    @Override
    public List<PriceHistory> findAll() {
        return priceHistoryRepository.findAll();
    }

    @Override
    public Optional<PriceHistory> findById(int id) {
        return priceHistoryRepository.findById(id);
    }
}
