package testClasses;

/**
 * author Love
 */

import base.BaseClass;
import com.google.common.collect.Maps;
import constants.Defaults;
import constants.InventoryItems;
import constants.ItemAmountType;
import constants.Messages;
import lombok.extern.java.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import requests.JupiterUser;
import testSteps.CartSteps;
import testSteps.ContactPageSteps;
import testSteps.HomePageSteps;
import testSteps.ShopPageSteps;
import utilities.loggerutilities.LogUtility;
import utilities.readerutilities.PropertyReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * author Love
 */
public class JupiterClass extends BaseClass {
    private HomePageSteps homePageSteps;
    private CartSteps cartSteps;
    private ContactPageSteps contactPageSteps;
    private ShopPageSteps shopPageSteps;
    private JupiterUser jupiterUser = new JupiterUser();
    private final Properties defaultConfigProp = PropertyReader.readProperty(Defaults.DEFAULT_CONFIG_PROPERTIES_PATH);


    @BeforeMethod
    public void setUp() {
        homePageSteps = new HomePageSteps(sessionManager);
        contactPageSteps = new ContactPageSteps(sessionManager);
        shopPageSteps = new ShopPageSteps(sessionManager);
        cartSteps = new CartSteps(sessionManager);
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
        Assert.assertTrue(itemsListWithQuantity.entrySet().stream().allMatch(item -> item.getValue().equals(cartSteps.getItemQuantity(item.getKey().getItemName()))));
    }

    @Test
    public void verifyPriceOfEachProduct() {
        homePageSteps.navigateToShopTab();
        Map<InventoryItems, Integer> itemsListWithQuantity = new HashMap();
        itemsListWithQuantity.put(InventoryItems.STUFFED_FROG, 2);
        itemsListWithQuantity.put(InventoryItems.FLUFFY_BUNNY, 5);
        itemsListWithQuantity.put(InventoryItems.VALENTINE_BEAR, 3);
        Map<InventoryItems, Double> expectedItemPrice = shopPageSteps.getItemPrice(itemsListWithQuantity);
        shopPageSteps.addItemsToCart(itemsListWithQuantity);
        //Add items to cart
        shopPageSteps.clickCartButton();
        //verify price of each product
        Map<InventoryItems, Double> actualItemPrice = cartSteps.getItemPriceOrSubtotal(itemsListWithQuantity, ItemAmountType.PRICE);
        LogUtility.info("Actual Item price : " + actualItemPrice);
        LogUtility.info("Expected Item price : " + expectedItemPrice);
        Assert.assertTrue(Maps.difference(actualItemPrice, expectedItemPrice).areEqual());
        //verify each product subtotal
        Map<InventoryItems, Double> expectedSubTotalAmount = shopPageSteps.getExpectedSubTotalAmount(itemsListWithQuantity, actualItemPrice);
        Map<InventoryItems, Double> actualSubTotalAmount = cartSteps.getItemPriceOrSubtotal(itemsListWithQuantity, ItemAmountType.SUBTOTAL);
        LogUtility.info("Actual Items Subtotal : " + actualSubTotalAmount);
        LogUtility.info("Expected Items Subtotal : " + expectedSubTotalAmount);
        Assert.assertTrue(Maps.difference(actualItemPrice, expectedItemPrice).areEqual());
        //Verify Total price
        Double expectedTotalPrice = expectedSubTotalAmount.values().stream().mapToDouble(subtotal -> subtotal).sum();
        Double actualTotalPrice = cartSteps.getTotalPrice();
        LogUtility.info("Expected Total Price : " + expectedTotalPrice);
        LogUtility.info("Actual Total Price : " + actualTotalPrice);
        Assert.assertEquals(expectedItemPrice, actualItemPrice);
    }

}
