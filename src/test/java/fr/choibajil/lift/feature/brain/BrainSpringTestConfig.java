package fr.choibajil.lift.feature.brain;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class BrainSpringTestConfig {

    @Bean
    public BrainScenarioState buildingBrainScenarioState() {
        return new BrainScenarioState();
    }

}
