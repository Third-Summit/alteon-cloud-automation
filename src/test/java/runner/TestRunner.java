package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		
		features = "src/test/java/Features/ShareProject.feature", //"src/test/java/Features/feature name if you want to run a specific feature
		glue = "stepDefinition",
		monochrome = true, //displays console output in a readable format
		//strict = true,//it will check if steps are not defined in step definition file
		dryRun = false,
		 plugin = {
	                "json:target/cucumber.json"
	        })
public class TestRunner extends AbstractTestNGCucumberTests {

}
