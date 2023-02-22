package test.bots;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * @author  Muthukumar Ramaiyah (@testbots-tamil youtube channel)
 * @version 1.0
 * @since   2023-02-01
 */
@CucumberOptions(features = {"src/test/resources"}, plugin = {"json:target/cucumber.json",
        "html:target/cucumber-html-reports/consolidated.html"})
public class RunnerTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

