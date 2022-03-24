package co.com.sofka.task.jsonplaceholder;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetUserAlbums implements Task {
    private String resource;

    public GetUserAlbums usingResource(String resource) {
        this.resource = resource;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resource)
        );
    }

    public static GetUserAlbums getUserAlbums() {
        return new GetUserAlbums();
    }
}
