package co.com.sofka.task.jsonplaceholder;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Patch;

public class PatchTitle implements Task {
    private String resource;
    private String bodyRequest;

    public PatchTitle usingResource(String resource) {
        this.resource = resource;
        return this;
    }

    public PatchTitle withBody(String bodyRequest) {
        this.bodyRequest = bodyRequest;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Patch.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                                        .body(bodyRequest)
                        )
        );
    }

    public static PatchTitle patchTitle() {
        return new PatchTitle();
    }
}
