package co.com.sofka.question.reqres;

import co.com.sofka.model.reqres.NewUserResponseModel;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class NewUserResponseData implements Question {
    @Override
    public NewUserResponseModel answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(NewUserResponseModel.class);
    }
}
