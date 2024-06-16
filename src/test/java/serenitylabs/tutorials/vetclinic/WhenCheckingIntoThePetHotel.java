package serenitylabs.tutorials.vetclinic;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import serenitylabs.tutorials.vetclinic.model.Pet;
import serenitylabs.tutorials.vetclinic.model.PetHotel;
import serenitylabs.tutorials.vetclinic.tasks.CheckIn;
import serenitylabs.tutorials.vetclinic.tasks.CheckOut;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;


@ExtendWith(SerenityJUnit5Extension.class)
public class WhenCheckingIntoThePetHotel {

    @Test
    public void petra_books_her_pet_cat_into_the_hotel() {

        // GIVEN
        Actor petra = Actor.named("Petra");
        Pet ginger = Pet.cat().named("Ginger");
        PetHotel hotel = new PetHotel("Cat Hotel");

        // WHEN
        petra.attemptsTo(
                CheckIn.aPet(ginger).into(hotel)
        );

        // THEN
        assertThat(hotel.getPets(), hasItem(ginger));
    }

    @Test
    public void petra_checks_her_cat_out_of_the_hotel() {

        // GIVEN
        Actor petra = Actor.named("Petra");
        Pet fluffy = Pet.cat().named("Fluffy");
        PetHotel hotel = new PetHotel("Cat Hotel");

        petra.wasAbleTo(CheckIn.aPet(fluffy).into(hotel));

        // WHEN
        petra.attemptsTo(
                CheckOut.aPet(fluffy).from(hotel)
        );

        // THEN
        assertThat(hotel.getPets(), not(hasItem(fluffy)));
    }
}
