package co.com.sofka.stepdefinition.reqres;

import co.com.sofka.model.reqres.NewUserRequestModel;
import co.com.sofka.model.reqres.NewUserResponseModel;
import co.com.sofka.question.reqres.NewUserResponseData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static co.com.sofka.question.ResponseCode.responseCode;
import static co.com.sofka.task.reqres.PostNewUser.postNewUser;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserStepDefinition {
    private static final Logger LOGGER = Logger.getLogger(CreateUserStepDefinition.class);

    private static final String URL_BASE = "https://reqres.in/api/";
    private static final String RESOURCE = "users";
    private NewUserRequestModel newUserRequestModel;

    private final Actor actor = Actor.named("Juanman");

    @Given("El cliente se encuentra en el recurso web")
    public void elClienteSeEncuentraEnElRecursoWeb() {
        PropertyConfigurator.configure(System.getProperty("user.dir") + LOG4J_PROPERTIES_FILE_PATH.getValue());
        actor.whoCan(CallAnApi.at(URL_BASE));
    }

    @When("El cliente crea un usuario con el nombre {string} y su trabajo {string}")
    public void elClienteCreaUnUsuarioConElNombreYSuTrabajo(String name, String job) {
        newUserRequestModel = new NewUserRequestModel();
        newUserRequestModel.setName(name);
        newUserRequestModel.setJob(job);
        actor.attemptsTo(
            postNewUser()
                    .usingResource(RESOURCE)
                    .withBody(newUserRequestModel)
        );
    }

    @Then("El sistema mostrara un codigo de respuesta exitoso y los datos del usuario creado")
    public void elSistemaMostraraUnCodigoDeRespuestaExitosoYLosDatosDelUsuarioCreado() {
        NewUserResponseModel newUserResponseModel = new NewUserResponseData().answeredBy(actor);
        actor.should(
                seeThat("El codigo de respuesta", responseCode(), equalTo(HttpStatus.SC_CREATED)),
                seeThat("El nombre del usuario debe ser", act -> newUserResponseModel.getName(),
                        equalTo(newUserRequestModel.getName())),
                seeThat("El trabajo del usuario debe ser", act -> newUserResponseModel.getJob(),
                        equalTo(newUserRequestModel.getJob()))
        );
    }
}
