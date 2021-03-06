import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Random;

public class SberbankTest extends BaseTest {
    ArrayList<String> regions;
    String regionName;

    @Before
    public void generateRegions() {
        regions = new ArrayList<>();
        regions.add("Нижегородская область");
        regions.add("Москва");
        regions.add("Камчатский край");
        regions.add("Удмуртская Республика");
        regions.add("Республика Бурятия");
    }


    /* Элементы, использующиеся в тесте */

    @FindBy(className = "region-list__arrow")
    WebElement regionArrow; // Кнопка выбора региона

    @FindBy(xpath = ".//div[@class = 'region-search-box']//input")
    WebElement regionInput; // Поле ввода региона

    @FindBy(className = "region-list__name")
    WebElement regionListName; // Текущий выбранный регион

    @FindBy(className = "footer-info")
    WebElement footer; // Футер страницы

    @FindBy(xpath = ".//li[@class = 'social__item']//*[@class = 'social__icon social__icon_type_fb']")
    WebElement facebookIcon; // Иконка Фейсбук

    @FindBy(xpath = ".//li[@class = 'social__item']//*[@class = 'social__icon social__icon_type_tw']")
    WebElement twitterIcon; // Иконка Твиттер

    @FindBy(xpath = ".//li[@class = 'social__item']//*[@class = 'social__icon social__icon_type_yt']")
    WebElement youTubeIcon; // Иконка Ютуб

    @FindBy(xpath = ".//li[@class = 'social__item']//*[@class = 'social__icon social__icon_type_ins']")
    WebElement instagramIcon; // Иконка Инстаграм

    @FindBy(xpath = ".//li[@class = 'social__item']//*[@class = 'social__icon social__icon_type_vk']")
    WebElement vkontakteIcon; // Иконка Вконтакте

    @FindBy(xpath = ".//li[@class = 'social__item']//*[@class = 'social__icon social__icon_type_ok']")
    WebElement odnoklassnikiIcon; // Иконка Одноклассники


    // Поиск опции выбора региона
    public WebElement findRegionOption() {
        WebElement findRegionOption = driver.findElement(By.xpath(".//u[contains(text(), '" + regionName + "')]"));
        return findRegionOption;
    }

    // Генерирует случайный регион для теста
    public String generateRegion() {
        Random randomIndex = new Random();
        String regionName = regions.get(randomIndex.nextInt(regions.size()));
        return regionName;
    }

    // Выбор региона из выпадающего меню
    public void pickRegion() {
        waitVisibility(regionArrow);
        regionArrow.click();

        regionInput.sendKeys(regionName);

        waitVisibility(findRegionOption());
        findRegionOption().click();
    }

    // Проверка отображения выбранного региона
    public void checkRegion() {
        waitVisibility(regionListName);
        Assert.assertTrue("Регион не равен заданному в тесте", regionListName.getText().contains(regionName));
    }

    // Прокрутка до футера сайта
    public void scrollToFooter() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                footer);
        waitVisibility(footer);
    }

    // Проверка отображения иконок соцсетей
    public void checkIcons() {
        Assert.assertTrue("Иконка Фейсбук невидима", facebookIcon.isDisplayed());
        Assert.assertTrue("Иконка Твиттер невидима", twitterIcon.isDisplayed());
        Assert.assertTrue("Иконка Ютуб невидима", youTubeIcon.isDisplayed());
        Assert.assertTrue("Иконка Инстаграм невидима", instagramIcon.isDisplayed());
        Assert.assertTrue("Иконка Вконтакте невидима", vkontakteIcon.isDisplayed());
        Assert.assertTrue("Иконка Одноклассники невидима", odnoklassnikiIcon.isDisplayed());
    }

    @Test
    public void sberbank_test() {
        PageFactory.initElements(driver, this);

        // Генерация случайного имени региона
        regionName = generateRegion();

        // Выбор региона из выпадающего меню
        pickRegion();
        // Проверка отображения выбранного региона
        checkRegion();

        // Прокрутка до футера сайта
        scrollToFooter();

        // Проверка отображения иконок соцсетей
        checkIcons();
    }
}
