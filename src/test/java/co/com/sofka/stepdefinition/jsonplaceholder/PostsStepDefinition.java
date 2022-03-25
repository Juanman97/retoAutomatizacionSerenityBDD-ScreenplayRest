package co.com.sofka.stepdefinition.jsonplaceholder;

import co.com.sofka.model.jsonplaceholder.PatchTitleModel;
import co.com.sofka.question.jsonplaceholder.PostData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static co.com.sofka.question.jsonplaceholder.ResponseCode.responseCode;
import static co.com.sofka.task.jsonplaceholder.DeletePost.deletePost;
import static co.com.sofka.task.jsonplaceholder.PatchTitle.patchTitle;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostsStepDefinition {
    private static final Logger LOGGER = Logger.getLogger(PostsStepDefinition.class);

    private static final String URL_BASE = "https://jsonplaceholder.typicode.com/";
    private static final String RESOURCE = "posts/%s";
    private String NEW_TITLE_ASSERTION;

    private final Actor actor = Actor.named("Juanman");

    @Given("EL usuario se encuentra en el recurso web")
    public void elUsuarioSeEncuentraEnElRecursoWeb() {
        PropertyConfigurator.configure(System.getProperty("user.dir") + LOG4J_PROPERTIES_FILE_PATH.getValue());
        actor.whoCan(CallAnApi.at(URL_BASE));
    }

    @When("El usuario actualiza el titulo del post con id {int} con el nuevo titulo {string}")
    public void elUsuarioActualizaElTituloDelPostConIdConElNuevoTitulo(Integer id, String newTitle) {
        NEW_TITLE_ASSERTION = newTitle;
        PatchTitleModel patchTitleModel = new PatchTitleModel();
        patchTitleModel.setTitle(newTitle);

        actor.attemptsTo(
                patchTitle()
                        .usingResource(String.format(RESOURCE, id))
                        .withBody(patchTitleModel)
        );
    }

    @Then("El sistema mostrara el post con el titulo actualizado")
    public void elSistemaMostraraElPostConElTituloActualizado() {
        String responsePostNewTitle = new PostData().answeredBy(actor).getTitle();
        actor.should(
                seeThat("El código de respuesta", responseCode(), equalTo(HttpStatus.SC_OK)),
                seeThat("El título del post es", act -> responsePostNewTitle, equalTo(NEW_TITLE_ASSERTION))
        );
    }

    @When("El usuario hace la peticion para eliminar el post con id {int}")
    public void elUsuarioHaceLaPeticionParaEliminarElPostConId(Integer id) {
        actor.attemptsTo(
                deletePost()
                        .usingResource(String.format(RESOURCE, id))
        );
    }

    @Then("El codigo de respuesta de la peticion sera {int}")
    public void elCodigoDeRespuestaDeLaPeticionSera(Integer httpStatusCode) {
        actor.should(
                seeThat("El código de respuesta ", responseCode(), equalTo(httpStatusCode))
        );
    }
}
