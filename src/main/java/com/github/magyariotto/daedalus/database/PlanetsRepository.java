package com.github.magyariotto.daedalus.database;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlanetsRepository extends CrudRepository<Planets, UUID> {
    Optional<Planets> findByCoordinate(String coordinate);
}
