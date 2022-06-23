package org.example;

import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class StepImplementation extends BaseTest {

    @Step("Browserı aç")
    public void openBrowser() {
        String fexxUrl = "https://testcase.myideasoft.com/";
        driver.get(fexxUrl);
    }

    @Step("<Key> saniye bekle")
    public void waitSecond(int Key) throws InterruptedException{
        Thread.sleep(Key*1000);
    }

    @Step("<urun> kelimesini arat")
    public void productSearch(String urun) throws InterruptedException{
        WebElement sendProduct = driver.findElement(By.cssSelector("input[name='q']"));
        sendProduct.sendKeys(urun);
        sendProduct.sendKeys(Keys.ENTER);
    }

    @Step("Ürün detay sayfasına git ve <adet> adet sepete ekle")
    public void productDetail(String adet) throws InterruptedException{
        WebElement clickProduct = driver.findElement(By.cssSelector("img.lazyload"));
        clickProduct.click();
        Select quantity = new Select(driver.findElement(By.cssSelector("select#qty-input")));
        quantity.selectByValue(adet);
        driver.findElement(By.cssSelector("a.add-to-cart-button")).click();
        /*for (int i=0;i<5;i++) {

            driver.findElement(By.cssSelector("a.add-to-cart-button")).click();

        }*/

    }
    @Step("Sepet eklenmiştir kontrolü")
    public void checkBasketMessage() throws InterruptedException{

        String actualText = driver.findElement(By.cssSelector("div.shopping-information-cart-inside")).getText();
        String expectedText = "SEPETİNİZE EKLENMİŞTİR";
        assertTrue(actualText.contains(expectedText));
        System.out.println(actualText+"="+expectedText);

    }

    @Step("Sepete git ve içeriği kontrol et")
    public void goToBasket() throws Throwable{

        WebDriverWait wait = new WebDriverWait(BaseTest.driver, 10);
        WebElement clickBasket = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[title='Sepet']")));
        clickBasket.click();
        String  productQuantity =driver.findElement(By.cssSelector("input.form-control")).getAttribute("value");
        int expectedProductQuantity = 5;
        System.out.println(productQuantity+"="+expectedProductQuantity);
    }
}
