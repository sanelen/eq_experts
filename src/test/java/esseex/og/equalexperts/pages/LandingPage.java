package esseex.og.equalexperts.pages;

import Utilities.TestDataProperty;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.pages.WebElementFacade;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Utilities.TestDataProperty.*;
import java.util.List;
import static Utilities.DataItem.*;
import static Utilities.WebElementLocators.*;
import java.text.SimpleDateFormat;

@DefaultUrl("http://hotel-test.equalexperts.io/")
public class LandingPage extends PageObject {

    @FindBy(id="firstname")  WebElementFacade firstname;
    @FindBy(id="lastname")  WebElementFacade lastname;
    @FindBy(id="totalprice")  WebElementFacade totalprice;
    @FindBy(id="checkin")  WebElementFacade checkin;
    @FindBy(id="checkout")  WebElementFacade checkout;
    @FindBy(id="depositpaid")  WebElementFacade deposit;
    @FindBy(id="bookings") WebElementFacade bookings;
    @FindBy(xpath = "//*[@class ='row']") WebElementFacade table;
    String row_items =  "//*[@class ='row']";


    public List<String> getBookings() {
        return bookings.findElements(By.tagName("p")).stream()
                             .map( element -> element.getText() )
                             .collect(Collectors.toList());
    }

    public void createBooking(boolean populateData) throws InterruptedException {
        if (populateData)
            createBookingPopulateData();
        else
            clickCreateBookingButton();
    }

    private void clickCreateBookingButton() throws InterruptedException {
        $(By.xpath(CREATE_BOOKING)).click();
         WaitforUpdate();
    }

    public void createBookingPopulateData() throws InterruptedException {
        firstname.type(TEST_DATA_FIRSTNAME);
        lastname.type(TEST_DATA_SURNAME);
        totalprice.type(TEST_DATA_TOTAL_PRICE);
        deposit.setWindowFocus();
        deposit.click();
        deposit.selectByIndex(TEST_DATA_DEPOSIT);
        checkin.typeAndEnter(TEST_DATA_CHECKIN);
        checkout.typeAndEnter(TEST_DATA_CHECKOUT);

        clickCreateBookingButton();

    }

    public void createBookingToDelete() throws InterruptedException {
        firstname.type(TEST_DATA_DOUBLEBOOKING);
        lastname.type(TEST_DATA_SURNAME);
        totalprice.type(TEST_DATA_TOTAL_PRICE);
        deposit.setWindowFocus();
        deposit.click();
        deposit.selectByIndex(TEST_DATA_DEPOSIT);
        checkin.typeAndEnter(TEST_DATA_CHECKIN);
        checkout.typeAndEnter(TEST_DATA_CHECKOUT);

        clickCreateBookingButton();
    }

    public void deleteBooking() {

        String DeleteBtnPath;
        String FirstNamepath;
        String getNameValue;

        List<WebElement> row_count = getDriver().findElements(By.xpath(row_items));

        int num_of_bookings = row_count.size() - 1;

        for(int i=2;i<= num_of_bookings;i++){
            System.out.println(i);
            //Path to FirstName column
            FirstNamepath =  "//div[" + i + "]/div/p";
            //Path to Delete buttons
            DeleteBtnPath =  "//div["+ i + "]/div[7]/input ";

            getNameValue = (getDriver().findElement(By.xpath(FirstNamepath)).getText()).trim();
            System.out.println(getNameValue);

            if (getNameValue.equals(TEST_DATA_DOUBLEBOOKING))
            {
                WebElement Ele_delete = getDriver().findElement(By.xpath(DeleteBtnPath));
                waitForCondition().until(ExpectedConditions.elementToBeClickable(Ele_delete)).click();
                break;
            }

        }
    }

    public void WaitforUpdate() throws InterruptedException {

        List<WebElement> row_count_before = getDriver().findElements(By.xpath(row_items));
        List<WebElement> row_count_after = row_count_before ;

        Integer loop_count = 0;
        while (loop_count < 3 && row_count_before == row_count_after ){

            getDriver().navigate().refresh();

            row_count_after = getDriver().findElements(By.xpath(row_items));
            java.lang.Thread.sleep(1000);
            loop_count ++;

        }
    }

    public static void  updateNames() throws IOException {
        String dataFirstname = TEST_DATA_FIRSTNAME;
        String dataDouble = TEST_DATA_DOUBLEBOOKING;
        //get current seconds
        String isoDatePattern = "ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
        String dateString = simpleDateFormat.format(new Date());
        System.out.println(dateString);
        TestDataProperty td = new TestDataProperty();
        //Remove numbers from string
        dataFirstname = dataFirstname.replaceAll("[0-9]", "");
        dataDouble = dataDouble.replaceAll("[0-9]", "");
        //Add new number to string
        String value = dataFirstname + dateString;
        String value1 = dataDouble + dateString;

        td.updatePropertyValue("firstname",value);
        td.updatePropertyValue("badbooking",value1);
    }
}