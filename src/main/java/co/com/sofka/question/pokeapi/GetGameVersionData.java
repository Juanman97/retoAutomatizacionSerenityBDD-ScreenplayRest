package co.com.sofka.question.pokeapi;

import co.com.sofka.model.pokeapi.gameversion.GameVersionModel;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetGameVersionData implements Question {
    @Override
    public GameVersionModel answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(GameVersionModel.class);
    }
}
