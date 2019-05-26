package pl.blu911.currencyexchange.exchangerate;

import org.junit.Test;
import pl.blu911.currencyexchange.exchangerate.ExchangeRate;
import pl.blu911.currencyexchange.exchangerate.ExchangeRateClient;

import static org.junit.Assert.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ExchangeRateClientTest {

    @Test
    public void shouldReturnExchangeRateNotNull() {

        ExchangeRate returnedExchangeRate = ExchangeRateClient.getRealTimeExchangeRate("USD", "JPY");

        assertNotNull(returnedExchangeRate.getExchangeRate());
    }

    @Test
    public void shouldContainSpecifiedExchangeRateObject() {
        List<ExchangeRate> returnedExchangeRates = ExchangeRateClient.getHistoricalExchangeRates("EUR", "USD", "full");

        ExchangeRate expectedExchangeRate = new ExchangeRate(
                new BigDecimal("1.1231"),
                LocalDate.parse("2019-05" +
                        "-13"), null);

        assertTrue(returnedExchangeRates.contains(expectedExchangeRate));
    }
}