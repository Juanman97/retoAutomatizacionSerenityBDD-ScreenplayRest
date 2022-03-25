package co.com.sofka.runner.pokeapi;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"co.com.sofka.stepdefinition.pokeapi"},
        features = {"src/test/resources/features/pokeapi/pokemonGameVersion.feature"}
)
public class PokemonGameVersionTest {
}
