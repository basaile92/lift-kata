package fr.choibajil.lift.feature.monitor;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "fr.choibajil.lift.feature.monitor",
        features = "classpath:cucumber/monitor")
@CucumberContextConfiguration
@SpringBootTest(classes = MonitorSpringTestConfig.class)
public class RunMonitorCucumberTests {
}
