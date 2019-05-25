package pl.blu911.currencyexchange.client;

import org.junit.Test;
import pl.blu911.currencyexchange.model.ExchangeRate;
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
                "EUR",
                "USD",
                new BigDecimal("1.1231"),
                LocalDate.parse("2019-05" +
                        "-13"), null);

        assertTrue(returnedExchangeRates.contains(expectedExchangeRate));
    }
}