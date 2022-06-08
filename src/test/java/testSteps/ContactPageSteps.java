package testSteps;

import library.WebLibrary;
import org.openqa.selenium.By;
import requests.JupiterUser;
import sessions.uisessions.SessionManager;

/**
 * author Love
 */
public class ContactPageSteps {
    private final By forenameInputField = By.id("forename");
    private final By surNameInputField = By.id("surname");
    private final By emailInputField = By.id("email");
    private final By telephoneInputField = By.id("telephone");
    private final By messageInputField = By.id("message");
    private final By submitButton = By.xpath("//a[text()='Submit']");
    private final By headerErrorMessage = By.id("header-message");
    private final By foreNameErrorMessage = By.id("forename-err");
    private final By emailErrorMessage = By.id("email-err");
    private final By messageErrorMessage = By.id("message-err");
    private final By successMessage = By.xpath("//div[@class='alert alert-success']");
    private final WebLibrary webLibrary;

    public ContactPageSteps(SessionManager sessionManager) {
        webLibrary = new WebLibrary(sessionManager);
    }

    public void enterForeName(JupiterUser user) {
        webLibrary.enterText(forenameInputField, user.getForeName());
    }

    public void fillMandatoryFields(JupiterUser user){
        enterForeName(user);
        enterEmail(user);
        enterMessage(user);
    }

    public void enterSurName(JupiterUser user) {
        webLibrary.enterText(surNameInputField, user.getSurName());
    }

    public void enterEmail(JupiterUser user) {
        webLibrary.enterText(emailInputField, user.getEmail());
    }

    public void enterTelephone(JupiterUser user) {
        webLibrary.enterText(telephoneInputField, user.getTelephone());
    }

    public void enterMessage(JupiterUser user) {
        webLibrary.enterText(messageInputField, user.getMessage());
    }

    public void clickSubmitButton() {
        webLibrary.click(submitButton);
    }

    public String getHeaderErrorMessage() {
        return webLibrary.getText(headerErrorMessage);
    }

    public boolean isHeaderErrorMessageDisplayed() {
        return webLibrary.isElementVisible(headerErrorMessage);
    }

    public String getForeNameErrorMessage() {
        return webLibrary.getText(foreNameErrorMessage);
    }

    public boolean isForeNameErrorMessageDisplayed() {
        return webLibrary.isElementVisible(foreNameErrorMessage);
    }

    public String getEmailErrorMessage() {
        return webLibrary.getText(emailErrorMessage);
    }

    public boolean isEmailErrorMessageDisplayed() {
        return webLibrary.isElementVisible(emailErrorMessage);
    }

    public String getMessageErrorMessage() {
        return webLibrary.getText(messageErrorMessage);
    }

    public boolean isMessageErrorMessageDisplayed() {
        return webLibrary.isElementVisible(messageErrorMessage);
    }

    public String getSuccessMessage() {
        return webLibrary.getText(successMessage);
    }

}
