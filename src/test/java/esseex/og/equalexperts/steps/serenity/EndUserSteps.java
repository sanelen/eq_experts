package esseex.og.equalexperts.steps.serenity;

import esseex.og.equalexperts.pages.LandingPage;
import net.thucydides.core.annotations.Step;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

import static Utilities.DataItem.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EndUserSteps {

    LandingPage landingPage;


    public void set_up() throws IOException {
        landingPage.updateNames();
    }

    @Step
    public void should_see_booking(String anyTextFromBookingCaptured) {
        assertThat(landingPage.getBookings(), hasItem(containsString(anyTextFromBookingCaptured)));
    }

    public void should_not_see_booking(String fromBookingCaptured){
        assertThat(landingPage.getBookings(), hasItem(not(containsString(fromBookingCaptured))));
    }

    @Step
    public void is_the_home_page_open() {
        landingPage.open();
    }

    @Step
    public void capture_booking(boolean populateData) throws InterruptedException {
        landingPage.createBooking(populateData);
    }
    @Step
    public void cancel_booking() throws InterruptedException {
        landingPage.createBookingToDelete();
        landingPage.deleteBooking();

    }
    @After
    public void Shut_down(){
        landingPage.getDriver().close();
    }


}