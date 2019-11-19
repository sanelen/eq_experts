package esseex.og.equalexperts.features.bookings;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Steps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import esseex.og.equalexperts.steps.serenity.EndUserSteps;

import java.io.IOException;

import static Utilities.DataItem.*;


@RunWith(SerenityRunner.class)
public class HotelBookingStory{

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public EndUserSteps san;

    @Before
    public void StartSet_Up() throws IOException {
        san.set_up();
    }


    @Issue("#EQUAL-EXPERTS-HOTEL    String TEST_DATA_TOTAL_CHECKOUT = getDataItem(\"equalExperts.checkout\");-BOOKING")
    @Test
    public void creating_a_booking_entry() throws InterruptedException, IOException {
        san.is_the_home_page_open();
        san.capture_booking(true);
        san.should_see_booking(TEST_DATA_FIRSTNAME);
    }

    @Test
    public void creating_a_booking_entry_THEN_DELETE() throws InterruptedException {
        san.cancel_booking();
        san.should_not_see_booking(TEST_DATA_DOUBLEBOOKING);
    }

    @Test
    public void creating_a_booking_entry_no_data() throws InterruptedException {
        san.is_the_home_page_open();
        san.capture_booking(false);
        san.should_not_see_booking(" ");
    }

    @Pending
    @Test
    public void creating_a_booking_entry_no_empty_FROM_dates() {
    }

    @Pending
    @Test
    public void creating_a_booking_entry_no_empty_dates() {
    }

    @Pending
    @Test
    public void creating_a_booking_entry_no_empty_TO_date() {
    }

    @Pending
    @Test
    public void creating_a_booking_entry_no_empty_surname() {
    }

    @Pending
    @Test
    public void creating_a_booking_entry_no_empty_lastName() {
    }

    @After
    public void Shut_down(){

    }



} 