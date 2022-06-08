package testClasses;

import base.BaseClass;
import constants.Defaults;
import constants.InventoryItems;
import constants.Messages;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import requests.JupiterUser;
import testSteps.ContactPageSteps;
import testSteps.HomePageSteps;
import testSteps.ShopPageSteps;
import utilities.readerutilities.PropertyReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * author Love
 */
public class JupiterClass extends BaseClass {
    private HomePageSteps homePageSteps;
    private ContactPageSteps contactPageSteps;
    private ShopPageSteps shopPageSteps;
    private JupiterUser jupiterUser = new JupiterUser();
    private final Properties defaultConfigProp = PropertyReader.readProperty(Defaults.DEFAULT_CONFIG_PROPERTIES_PATH);


    @BeforeMethod
    public void setUp() {
        homePageSteps = new HomePageSteps(sessionManager);
        contactPageSteps = new ContactPageSteps(sessionManager);
        shopPageSteps = new ShopPageSteps(sessionManager);
        jupiterUser.setForeName(defaultConfigProp.getProperty(Defaults.TEST_FORENAME_KEY));
        jupiterUser.setEmail(defaultConfigProp.getProperty(Defaults.TEST_EMAIL_KEY));
        jupiterUser.setMessage(defaultConfigProp.getProperty(Defaults.TEST_MESSAGE_KEY));
    }

    @Test
    public void verifyValidationMessages() {
        SoftAssert softAssert = new SoftAssert();
        homePageSteps.navigateToContactPage();
        contactPageSteps.clickSubmitButton();
        // validate error messages
        softAssert.assertEquals(contactPageSteps.getHeaderErrorMessage(), Messages.CONTACT_HEADER_MANDATORY_FIELDS_REQUIRED);
        softAssert.assertEquals(contactPageSteps.getForeNameErrorMessage(), Messages.FORENAME_REQUIRED);
        softAssert.assertEquals(contactPageSteps.getEmailErrorMessage(), Messages.EMAIL_REQUIRED);
        softAssert.assertEquals(contactPageSteps.getMessageErrorMessage(), Messages.MESSAGE_REQUIRED);
        softAssert.assertAll();
        //Populate mandatory fields
        contactPageSteps.fillMandatoryFields(jupiterUser);
        //Verify errors are gone
        softAssert.assertFalse(contactPageSteps.isForeNameErrorMessageDisplayed());
        softAssert.assertFalse(contactPageSteps.isEmailErrorMessageDisplayed());
        softAssert.assertFalse(contactPageSteps.isMessageErrorMessageDisplayed());
        softAssert.assertAll();
    }

    @Test(invocationCount = 5)
    public void verifyContactPageSuccessfulSubmissionMessage() {
        homePageSteps.navigateToContactPage();
        //Populate mandatory fields
        contactPageSteps.fillMandatoryFields(jupiterUser);
        contactPageSteps.clickSubmitButton();
        //Verify successful submission message
        Assert.assertEquals(contactPageSteps.getSuccessMessage(), Messages.CONTACT_SUCCESS_MESSAGE.replace(Defaults.USERNAME_REGEX, jupiterUser.getForeName()));
    }

    @Test
    public void verifyCartItems() {
        homePageSteps.navigateToShopTab();
        Map<InventoryItems, Integer> itemsListWithQuantity = new HashMap();
        itemsListWithQuantity.put(InventoryItems.FUNNY_COW, 2);
        itemsListWithQuantity.put(InventoryItems.FLUFFY_BUNNY, 1);
        //Add items to cart
        shopPageSteps.addItemsToCart(itemsListWithQuantity);
        shopPageSteps.clickCartButton();
        //verify items in cart
    }

    @Test
    public void verifyPriceOfEachProduct(){
        homePageSteps.navigateToShopTab();
        Map<InventoryItems, Integer> itemsListWithQuantity = new HashMap();
        itemsListWithQuantity.put(InventoryItems.STUFFED_FROG,2);
        itemsListWithQuantity.put(InventoryItems.FLUFFY_BUNNY,5);
        itemsListWithQuantity.put(InventoryItems.VALENTINE_BEAR,3);
        shopPageSteps.addItemsToCart(itemsListWithQuantity);
        //Add items to cart
        shopPageSteps.clickCartButton();
        //verify price
    }

}
