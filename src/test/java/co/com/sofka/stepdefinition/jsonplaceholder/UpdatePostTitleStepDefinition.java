package co.com.sofka.stepdefinition.jsonplaceholder;

import co.com.sofka.question.jsonplaceholder.GetPostData;
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
import static co.com.sofka.task.jsonplaceholder.PatchTitle.patchTitle;
import static co.com.sofka.util.FileUtilities.updateTitleBodyConverter;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class UpdatePostTitleStepDefinition {
    private static final Logger LOGGER = Logger.getLogger(GetPostTitleStepDefinition.class);

    private static final String URL_BASE = "https://jsonplaceholder.typicode.com/";
    private static final String RESOURCE = "posts/%s";
    private static String BODY_REQUEST;
    private String NEW_TITLE_ASSERTION;

    private final Actor actor = Actor.named("Juanman");

    @Given("El usuario esta en el recurso web indicando el nuevo titulo {string}")
    public void elUsuarioEstaEnElRecursoWebIndicandoElNuevoTitulo(String newTitle) {
        PropertyConfigurator.configure(System.getProperty("user.dir") + LOG4J_PROPERTIES_FILE_PATH.getValue());
        actor.whoCan(CallAnApi.at(URL_BASE));
        BODY_REQUEST = updateTitleBodyConverter(newTitle);
        NEW_TITLE_ASSERTION = newTitle;
    }

    @When("El usuario actualiza el titulo del post con id {int}")
    public void elUsuarioActualizaElTituloDelPostConId(Integer id) {
        actor.attemptsTo(
                patchTitle()
                        .usingResource(RESOURCE)
                        .withBody(BODY_REQUEST)
        );
    }

    @Then("El sistema mostrara el post con el titulo actualizado")
    public void elSistemaMostraraElPostConElTituloActualizado() {
        String responsePostNewTitle = new GetPostData().answeredBy(actor).getTitle();
        actor.should(
                seeThat("El código de respuesta", responseCode(), equalTo(HttpStatus.SC_OK)),
                seeThat("El título del post es", act -> responsePostNewTitle, equalTo(NEW_TITLE_ASSERTION))
        );
    }
}
