package com.example.check.entity.saying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SayingRepository extends CrudRepository<Saying, Long> {

    @Query(value = "SELECT * FROM saying_tbl ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Saying> findRandomSaying();

}
