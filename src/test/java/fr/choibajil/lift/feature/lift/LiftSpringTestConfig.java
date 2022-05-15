package fr.choibajil.lift.feature.lift;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class LiftSpringTestConfig {

    @Bean
    public LiftScenarioState liftScenarioState() {
        return new LiftScenarioState();
    }
}
