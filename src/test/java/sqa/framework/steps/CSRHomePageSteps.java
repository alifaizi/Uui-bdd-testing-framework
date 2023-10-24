package sqa.framework.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sqa.framework.pages.CSRHomePage;
import sqa.framework.utilities.CommonUtilities;

public class CSRHomePageSteps extends CommonUtilities{
	
	@Then("validate user profile name to be {string}")
	public void validate_user_profile_name_to_be(String expectedProfileName) {
	   String text = getElementText(CSRHomePage.PROFILE_NAME_BUTTON);
	   String actualProfileName = text.substring(0, text.indexOf("account_circle")).trim();
	   assertEqual(expectedProfileName, actualProfileName);
	}
	
	@When("user logout")
	public void userLogout() {
	    clickElement(CSRHomePage.PROFILE_NAME_BUTTON);
	    clickElement(CSRHomePage.LOGOUT_BUTTON);
	}

}
