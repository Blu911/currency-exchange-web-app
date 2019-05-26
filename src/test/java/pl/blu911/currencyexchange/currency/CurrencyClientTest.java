package pl.blu911.currencyexchange.currency;

import org.junit.Test;
import pl.blu911.currencyexchange.currency.Currency;
import pl.blu911.currencyexchange.currency.CurrencyClient;

import java.util.List;
import static org.junit.Assert.*;

public class CurrencyClientTest {

    @Test
    public void shouldContainSpecifiedCurrencyObject() {
        List<Currency> returnedCurrencies = CurrencyClient.getCurrencies();

        Currency expectedCurrency = new Currency("USD", "United States Dollar");

        assertTrue(returnedCurrencies.contains(expectedCurrency));
    }

}