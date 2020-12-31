package com.github.magyariotto.daedalus.database;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Buildings {
    @Id
    public UUID planetId;
    public UUID userId;
    public Integer robotFactoryLevel;
    public Integer researchLaboratoryLevel;
    public Integer covenantLevel;
    public Integer rocketSilo;
    public Integer nanoTechnologyLevel;
    public Integer spaceDockLevel;
}
