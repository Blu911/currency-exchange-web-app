package pl.blu911.currencyexchange.client;

import org.junit.Test;
import pl.blu911.currencyexchange.model.Currency;
import java.util.List;

import static org.junit.Assert.*;

public class CurrencyClientTest {

    @Test
    public void shouldReturnSpecifiedCurrencyObject() {
        List<Currency> returnedCurrencies = CurrencyClient.getCurrencies();

        Currency expectedCurrency = new Currency("USD", "United States Dollar");

        assertTrue(returnedCurrencies.contains(expectedCurrency));
    }

}