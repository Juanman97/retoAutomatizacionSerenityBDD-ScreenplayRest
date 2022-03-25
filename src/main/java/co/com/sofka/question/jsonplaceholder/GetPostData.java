package co.com.sofka.question.jsonplaceholder;

import co.com.sofka.model.jsonplaceholder.PostModel;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetPostData implements Question<PostModel> {
    @Override
    public PostModel answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(PostModel.class);
    }
}
