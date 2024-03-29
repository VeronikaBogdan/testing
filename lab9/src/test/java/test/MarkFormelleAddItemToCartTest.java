package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class MarkFormelleAddItemToCartTest {
	private static final Duration WAIT_SECONDS = Duration.ofSeconds(15);
	private static final String EXPECTED_MOVE_TO_CART = "Перейти в корзину";
	private static final String EXPECTED_AMOUNT_OF_GOODS = "1";

	private WebDriver webDriver;
	private WebDriverWait webDriverWait;

	@BeforeMethod
	public void setUpBrowser() {
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		webDriverWait = new WebDriverWait(webDriver, WAIT_SECONDS);
		webDriver.get("https://markformelle.by/catalog/zhenshchinam/mf-life/bryuki-leginsy/182436-73175-1050/");
		realizeChooseProductSizeAndClickButtonGoToCart();
	}

	@Test
	public void testAddItemToCart() {
		WebElement amountOfGoods = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='num' and text()='1']")));
		webDriverWait.until(ExpectedConditions.visibilityOf(amountOfGoods));
		Assert.assertEquals(amountOfGoods.getText(),EXPECTED_AMOUNT_OF_GOODS);
	}

	@Test
	public void testChangeOfTheButtonGoToCart() {
		WebElement buttonGoToCart = webDriver.findElement(By.xpath("//a[@data-addpickup='N' and @href='/personal/cart/']"));
		webDriverWait.until(ExpectedConditions.visibilityOf(buttonGoToCart));
		Assert.assertEquals(buttonGoToCart.getText(),EXPECTED_MOVE_TO_CART);
	}

	@AfterMethod
	public void tearDown() {
		webDriver.quit();
	}

	private void realizeChooseProductSizeAndClickButtonGoToCart() {
		WebElement buttonOpenListOfSizes = webDriver.findElement(By.xpath("//div[@class='size-header closed']"));
		webDriverWait.until(ExpectedConditions.visibilityOf(buttonOpenListOfSizes));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(buttonOpenListOfSizes));
		buttonOpenListOfSizes.click();

		WebElement buttonSelectSize = webDriver.findElement(By.xpath("//div[@data-offer-id='484144']"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(buttonSelectSize));
		buttonSelectSize.click();

		WebElement buttonAddToCart = webDriver.findElement(By.xpath("//a[@href='javascript:void(0);']"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(buttonAddToCart));
		buttonAddToCart.click();
	}
}
