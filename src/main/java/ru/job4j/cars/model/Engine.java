package ru.job4j.cars.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "engine")
@Entity
@NoArgsConstructor
public class Engine {
    private int id;
}
