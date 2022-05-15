package fr.choibajil.lift.feature.building;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
class BuildingSpringTestConfig {

    @Bean
    public BuildingScenarioState buildingScenarioState() {
        return new BuildingScenarioState();
    }

}
