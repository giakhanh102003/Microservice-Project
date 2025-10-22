package org.example.catalogservice.repository;

import org.example.catalogservice.entity.Color;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends MongoRepository<Color,String> {
}
