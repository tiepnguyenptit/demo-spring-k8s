package com.example.demospringk8s.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private Double price;

    public BookEntity(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
