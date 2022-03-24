package co.com.sofka.stepdefinition.jsonplaceholder;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static co.com.sofka.question.jsonplaceholder.ResponseCode.responseCode;
import static co.com.sofka.question.jsonplaceholder.ResponseToString.responseToString;
import static co.com.sofka.task.jsonplaceholder.GetUserAlbums.getUserAlbums;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.containsString;

public class UserAlbumsStepDefinition {
    private static final Logger LOGGER = Logger.getLogger(UserAlbumsStepDefinition.class);

    private static final String URL_BASE = "https://jsonplaceholder.typicode.com/";
    private static final String RESOURCE = "users/%s/albums";
    private static final String RESPONSE_ASSERTION = "\"userId\": %s";

    private final Actor actor = Actor.named("Juanman");

    @Given("El usuario esta en el recurso web")
    public void elUsuarioEstaEnElRecursoWeb() {
        PropertyConfigurator.configure(System.getProperty("user.dir") + LOG4J_PROPERTIES_FILE_PATH.getValue());
        actor.whoCan(CallAnApi.at(URL_BASE));
    }

    @When("El usuario realiza la consulta para el id {int}")
    public void elUsuarioRealizaLaConsultaParaElId(int id) {
        actor.attemptsTo(
                getUserAlbums()
                        .usingResource(String.format(RESOURCE, id))
        );
    }

    @Then("El sistema mostrara la lista de albumes relacionados con el id {int}")
    public void elSistemaMostraraLaListaDeAlbumesRelacionadosConElId(int id) {
        actor.should(
                seeThat("El código de respuesta", responseCode(), equalTo(HttpStatus.SC_OK)),
                seeThat("La lista pertenece al id", responseToString(), containsString(String.format(RESPONSE_ASSERTION, id)))
        );
    }
}
