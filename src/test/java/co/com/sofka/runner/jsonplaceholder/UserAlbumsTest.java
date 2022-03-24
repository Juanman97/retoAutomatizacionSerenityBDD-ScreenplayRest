package co.com.sofka.runner.jsonplaceholder;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"co.com.sofka.stepdefinition.jsonplaceholder"},
        features = {"src/test/resources/features/jsonplaceholder/useralbums.feature"}
)
public class UserAlbumsTest {
}
