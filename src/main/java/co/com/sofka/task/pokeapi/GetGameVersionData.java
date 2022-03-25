package co.com.sofka.task.pokeapi;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetGameVersionData implements Task {
    private String resource;

    public GetGameVersionData usingResource(String resource) {
        this.resource = resource;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(resource)
        );
    }

    public static GetGameVersionData getGameVersionData() {
        return new GetGameVersionData();
    }
}
