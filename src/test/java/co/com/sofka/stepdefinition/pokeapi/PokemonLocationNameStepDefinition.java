package co.com.sofka.stepdefinition.pokeapi;

import co.com.sofka.question.pokeapi.GetIngameLocationName;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static co.com.sofka.question.jsonplaceholder.ResponseCode.responseCode;
import static co.com.sofka.task.pokeapi.GetIngameLocationData.getIngameLocationData;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class PokemonLocationNameStepDefinition {
    private static final Logger LOGGER = Logger.getLogger(PokemonLocationNameStepDefinition.class);

    private static final String URL_BASE = "https://pokeapi.co/api/";
    private static final String RESOURCE = "v2/location/%s/";

    private final Actor actor = Actor.named("Juanman");

    @Given("el usuario se encuentra en la api pokeapi")
    public void elUsuarioSeEncuentraEnLaApiPokeapi() {
        PropertyConfigurator.configure(System.getProperty("user.dir") + LOG4J_PROPERTIES_FILE_PATH.getValue());
        actor.whoCan(CallAnApi.at(URL_BASE));
    }

    @When("el usuario realiza la consulta para la ubicacion dentro del juego con id {int}")
    public void elUsuarioRealizaLaConsultaParaLaUbicacionDentroDelJuegoConId(Integer id) {
        actor.attemptsTo(
                getIngameLocationData()
                        .usingResource(String.format(RESOURCE, id))
        );
    }

    @Then("se visualizara que el nombre de la ubicacion {string}")
    public void seVisualizaraQueElNombreDeLaUbicacion(String locationName) {
        String assertionLocationName = new GetIngameLocationName().answeredBy(actor).getName();

        actor.should(
                seeThat("El código de respuesta debe ser " + HttpStatus.SC_OK, responseCode(),
                        equalTo(HttpStatus.SC_OK)),
                seeThat("El nobre de la ubicación debe ser " + locationName,
                        act -> assertionLocationName, equalTo(locationName))
        );
    }

}
