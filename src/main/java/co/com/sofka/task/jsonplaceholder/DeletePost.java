package co.com.sofka.task.jsonplaceholder;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

public class DeletePost implements Task {
    private String resource;

    public DeletePost usingResource(String resource) {
        this.resource = resource;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from(resource)
        );
    }

    public static DeletePost deletePost() {
        return new DeletePost();
    }
}
