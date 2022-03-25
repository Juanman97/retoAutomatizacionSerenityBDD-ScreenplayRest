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
import static co.com.sofka.task.jsonplaceholder.GetUserAlbums.getUserAlbums;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetPostTitleStepDefinition {
    private static final Logger LOGGER = Logger.getLogger(GetPostTitleStepDefinition.class);

    private static final String URL_BASE = "https://jsonplaceholder.typicode.com/";
    private static final String RESOURCE = "posts/%s";

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

    @Then("Se visualizara que el post tiene como titulo {string}")
    public void seVisualizaraQueElPostTieneComoTitulo(String postTitle) {
        String responsePostTitle = new GetPostData().answeredBy(actor).getTitle();
        actor.should(
                seeThat("El código de respuesta", responseCode(), equalTo(HttpStatus.SC_OK)),
                seeThat("La lista pertenece al id", act -> responsePostTitle, equalTo(postTitle))
        );
    }
}
