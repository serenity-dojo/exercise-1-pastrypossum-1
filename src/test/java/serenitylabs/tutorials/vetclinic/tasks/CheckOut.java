package serenitylabs.tutorials.vetclinic.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.thucydides.core.annotations.Step;
import serenitylabs.tutorials.vetclinic.model.Pet;
import serenitylabs.tutorials.vetclinic.model.PetHotel;

public class CheckOut implements Performable {

    private Pet pet;
    private PetHotel petHotel;

    public CheckOut(){}

    public CheckOut(Pet pet, PetHotel petHotel) {
        this.pet = pet;
        this.petHotel = petHotel;
    }

    public static CheckOutBuilder aPet(Pet pet) {
        return new CheckOutBuilder(pet);
    }

    @Override
    @Step("{0} checks out #pet from the hotel")
    public <T extends Actor> void performAs(T t) {
        petHotel.checkOut(pet);
    }

    public static class CheckOutBuilder {

        private Pet pet;

        public CheckOutBuilder(Pet pet) {
            this.pet = pet;
        }

        public Performable from(PetHotel petHotel){

            return Instrumented.instanceOf(CheckOut.class).withProperties(pet, petHotel);
        }
    }
}
