package co.com.sofka.stepdefinition.pokeapi;

import co.com.sofka.question.pokeapi.GetGameVersionData;
import co.com.sofka.stepdefinition.jsonplaceholder.GetPostTitleStepDefinition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static co.com.sofka.question.jsonplaceholder.ResponseCode.responseCode;
import static co.com.sofka.task.pokeapi.GetPokemonData.getPokemonData;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class PokemonGameVersionStepDefinition {
    private static final Logger LOGGER = Logger.getLogger(GetPostTitleStepDefinition.class);

    private static final String URL_BASE = "https://pokeapi.co/api/";
    private static final String RESOURCE = "v2/version/%s/";

    private final Actor actor = Actor.named("Juanman");

    @Given("el usuario se encuentra en la api")
    public void elUsuarioSeEncuentraEnLaApi() {
        PropertyConfigurator.configure(System.getProperty("user.dir") + LOG4J_PROPERTIES_FILE_PATH.getValue());
        actor.whoCan(CallAnApi.at(URL_BASE));
    }

    @When("el usuario realiza la consulta para la version del juego con id {int}")
    public void elUsuarioRealizaLaConsultaParaLaVersionDelJuegoConId(Integer gameID) {
        actor.attemptsTo(
                getPokemonData()
                        .usingResource(String.format(RESOURCE, gameID))
        );
    }

    @Then("se visualizara que la version del juego es {string}")
    public void seVisualizaraQueLaVersionDelJuegoEs(String gameName) {
        String responseGameName = new GetGameVersionData().answeredBy(actor).getName();
        actor.should(
                seeThat("El código de respuesta es " + HttpStatus.SC_OK, responseCode(), equalTo(HttpStatus.SC_OK)),
                seeThat("El nombre de la versión del juego debe ser " + gameName,
                        act -> responseGameName, equalTo(gameName))
        );
    }
}
