package fr.choibajil.lift.feature.building;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "fr.choibajil.lift.feature.building",
        features = "classpath:cucumber/building")
@CucumberContextConfiguration
@SpringBootTest(classes = BuildingSpringTestConfig.class)
public class RunBuildingCucumberTests {
}
