package pl.blu911.currencyexchange.exchangerate;

import java.util.List;

public interface ExchangeRateService {
    ExchangeRate getRealTimeRate(String fromCurrency, String toCurrency);

    List<ExchangeRate> getHistoricalRates(String fromCurrency, String toCurrency);

    List<List<ExchangeRate>> getHistoricalRatesInPeriods(List<ExchangeRate> exchangeRateList);
}
