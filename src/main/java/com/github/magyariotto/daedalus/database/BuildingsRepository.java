package com.github.magyariotto.daedalus.database;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BuildingsRepository extends CrudRepository<Buildings, UUID> {
}
