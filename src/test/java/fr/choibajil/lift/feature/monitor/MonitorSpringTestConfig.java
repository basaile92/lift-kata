package fr.choibajil.lift.feature.monitor;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MonitorSpringTestConfig {

    @Bean
    public MonitorScenarioState monitorScenarioState() {
        return new MonitorScenarioState();
    }
}
