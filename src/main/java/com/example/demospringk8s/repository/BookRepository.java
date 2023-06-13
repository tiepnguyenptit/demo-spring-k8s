package com.example.demospringk8s.repository;

import com.example.demospringk8s.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookEntity, String> {
}
