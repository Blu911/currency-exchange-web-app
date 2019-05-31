package pl.blu911.currencyexchange.exchangerate;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExchangeRateServiceTest {

    private ExchangeRateClient exchangeRateClient;
    private ExchangeRateService service;

    @Before
    public void setUp() {
        exchangeRateClient = mock(ExchangeRateClient.class);
        service = new ExchangeRateServiceImpl(exchangeRateClient);
    }

    @Test
    public void shouldReturnExchangeRateObjectWithSpecifiedApiNoteAndDate() {
        //given
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setApiNote(null);
        exchangeRate.setDate(LocalDate.now());
        when(exchangeRateClient.getRealTimeExchangeRate("EUR", "PLN")).thenReturn(exchangeRate);

        //when
        ExchangeRate realTimeRate = service.getRealTimeRate("EUR", "PLN");

        //then
        assertEquals(exchangeRate.getApiNote(), realTimeRate.getApiNote());
        assertEquals(exchangeRate.getDate(), realTimeRate.getDate());
    }

}