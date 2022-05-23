package fr.choibajil.lift.feature.brain;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "fr.choibajil.lift.feature.brain",
        features = "classpath:cucumber/brain")
@CucumberContextConfiguration
@SpringBootTest(classes = BrainSpringTestConfig.class)
public class RunBrainCucumberTests {
}
