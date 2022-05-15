package fr.choibajil.lift.feature.lift;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "fr.choibajil.lift.feature.lift",
        features = "classpath:cucumber/lift")
@CucumberContextConfiguration
@SpringBootTest(classes = LiftSpringTestConfig.class)
public class RunLiftCucumberTests {
}
