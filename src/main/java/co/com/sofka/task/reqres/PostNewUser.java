package co.com.sofka.task.reqres;

import co.com.sofka.model.reqres.NewUserRequestModel;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class PostNewUser implements Task {
    private String resource;
    private NewUserRequestModel newUserRequestModel;

    public PostNewUser usingResource(String resource) {
        this.resource = resource;
        return this;
    }

    public PostNewUser withBody(NewUserRequestModel newUserRequestModel) {
        this.newUserRequestModel = newUserRequestModel;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                                        .body(newUserRequestModel)
                        )
        );
    }

    public static PostNewUser postNewUser() {
        return new PostNewUser();
    }
}
