package com.github.magyariotto.daedalus.generator;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
public class PlanetGenerator {
    public int planetSize = (int) (Math.random() * 250 + 150);
    public int galaxyNumber = 1;
    public int systemNumber = (int) (Math.random() * 500 + 1);
    public int planetNumber = (int) (Math.random() * 15 + 1);
    public String planet = "%s:%s:%s";

    public String coordinate = String.format(planet,galaxyNumber,systemNumber,planetNumber);
}
