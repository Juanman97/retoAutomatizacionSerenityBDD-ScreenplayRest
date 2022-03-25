package co.com.sofka.runner.reqres;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"co.com.sofka.stepdefinition.reqres"},
        features = {"src/test/resources/features/reqres/createUser.feature"}
)
public class CreateUserTest {
}
