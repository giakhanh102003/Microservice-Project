package org.example.catalogservice.repository;

import org.example.catalogservice.entity.Size;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends MongoRepository<Size, String> {

}
