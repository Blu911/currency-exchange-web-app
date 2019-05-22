package pl.blu911.currencyexchange.client;

import org.junit.Test;
import pl.blu911.currencyexchange.model.Currency;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CurrenciesClientTest {

    @Test
    public void shouldReturnSpecifiedCurrencyObject() throws IOException {
        ArrayList<Currency> returnedCurrencies = CurrenciesClient.getCurrencies();

        Currency expectedCurrency = new Currency("USD", "United States Dollar");

        assertTrue(returnedCurrencies.contains(expectedCurrency));
    }

}