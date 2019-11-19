package Utilities;

import static Utilities.TestDataProperty.getDataItem;

public interface DataItem {
    String TEST_DATA_FIRSTNAME = getDataItem("equalExperts.firstname");
    String TEST_DATA_SURNAME = getDataItem("equalExperts.surname");
    String TEST_DATA_TOTAL_PRICE = getDataItem("equalExperts.totalPrice");
    Integer TEST_DATA_DEPOSIT = 1;
    String TEST_DATA_CHECKIN = getDataItem("equalExperts.checkin");
    String TEST_DATA_CHECKOUT = getDataItem("equalExperts.checkout");
    String TEST_DATA_DOUBLEBOOKING = getDataItem("equalExperts.badbooking");

}
