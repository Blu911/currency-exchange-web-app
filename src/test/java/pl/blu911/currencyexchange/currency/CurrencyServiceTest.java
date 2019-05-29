package pl.blu911.currencyexchange.currency;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.blu911.currencyexchange.client.HttpClient;
import java.util.List;

import static org.junit.Assert.*;

public class CurrencyServiceTest {
private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceTest.class);

private CurrencyService service;
private CurrencyClient currencyClient;

    @Before
    public void setUp() {
        currencyClient = new CurrencyClient(new HttpClient());
        service = new CurrencyServiceImpl(currencyClient);
    }

    @Test
    public void shouldContainSpecifiedCurrencyObject() {

        Currency expectedCurrency = new Currency("USD", "United States Dollar");

        List<Currency> returnedList = service.getAllCurrencies();

        assertTrue(returnedList.contains(expectedCurrency));
    }

}