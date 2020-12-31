package com.github.magyariotto.daedalus.database;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Planets {
    @Id
    public UUID planetId;
    public UUID userId;
    public String coordinate;
    public Integer fullSize;
    public Integer existsSize;
}
