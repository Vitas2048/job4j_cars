package ru.job4j.cars.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.cars.model.PriceHistory;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private int id;

    private String description;

    private String userName;

    private List<PriceHistory> priceHistories;

    private List<Integer> photosId;

    private LocalDateTime time;

    private String markName;

    private String model;

    private boolean sold;

    private int firstPhotoId;

    private long price;

    private String engine;

    private int userId;

    private String carBody;

}
