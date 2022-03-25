package co.com.sofka.question.pokeapi;

import co.com.sofka.model.pokeapi.LocationModel;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetIngameLocationName implements Question {
    @Override
    public LocationModel answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(LocationModel.class);
    }
}
