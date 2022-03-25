package co.com.sofka.question;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class ResponseToString implements Question <String> {
    @Override
    public String answeredBy(Actor actor) {
        return new String(LastResponse.received().answeredBy(actor).asByteArray());
    }

    public static ResponseToString responseToString() {
        return new ResponseToString();
    }
}
