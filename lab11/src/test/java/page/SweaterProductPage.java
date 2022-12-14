package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.LocatorUtil;

public class SweaterProductPage extends ProductPage {
	private final Logger logger = LogManager.getRootLogger();
	private static final String PRODUCT_PAGE_URL = "https://markformelle.by/catalog/zhenshchinam/mf-life/koftochki-longslivy/dzhempery-kombidresy/122722-74708-1050/";
//	private final By buttonProfile = By.xpath("//a[@data-mfp-src='/ajax/auth.php?page=/']");
	private final By buttonProfile = By.xpath("//a[contains(@class, 'header-profile')]");
	private final By inputLogin = By.xpath("//input[@name='USER_LOGIN']");
	private final By inputPassword = By.xpath("//input[@type='password']");
	private final By buttonSubmit = By.xpath("//input[@name='Login']");
	private final By wrongEmailMessage = By.xpath("//div[contains(@class,'desktop')]//div[@class='wrong-email']");
	private final By emptyEmailMessage = By.xpath("//div[contains(@class,'desktop')]//div[@class='empty-field']");
	private static final String INPUT_EMAIL_LOCATOR_PATTERN = "//div[contains(@class,'desktop')]//input[@type='%s']";
	private static final String EMAIL_MESSAGE_LOCATOR_PATTERN = "//div[contains(@class,'desktop')]//div[@class='%s']";

	public SweaterProductPage(WebDriver driver) {
		super(driver);
	}

	public SweaterProductPage login(User user) {
		waitUntilElementToBeClickableAndClick(buttonProfile);
		waitForPresenceOfElement(inputLogin).sendKeys(user.getEmail());
		waitForPresenceOfElement(inputPassword).sendKeys(user.getPassword());
		waitForPresenceOfElement(buttonSubmit).click();
		logger.info("Login performed");
		return this;
	}

	public SweaterProductPage subscribe(User user) {
		waitForPresenceOfElement(LocatorUtil.getLocatorByXpathPattern(INPUT_EMAIL_LOCATOR_PATTERN,"email"))
			.sendKeys(user.getEmail());
		waitUntilElementToBeClickableAndClick(LocatorUtil.getLocatorByXpathPattern(INPUT_EMAIL_LOCATOR_PATTERN,"submit"));
		return this;
	}

	public String getWrongEmailMessage() {
		return waitForPresenceOfElement(LocatorUtil.getLocatorByXpathPattern(EMAIL_MESSAGE_LOCATOR_PATTERN,"wrong-email"))
			.getText();
	}

	public String getEmptyEmailMessage() {
		return waitForPresenceOfElement(LocatorUtil.getLocatorByXpathPattern(EMAIL_MESSAGE_LOCATOR_PATTERN,"empty-field"))
			.getText();
	}

	@Override
	public SweaterProductPage openPage() {
		webDriver.get(PRODUCT_PAGE_URL);
		logger.info("Sweater page opened");
		return this;
	}
}
