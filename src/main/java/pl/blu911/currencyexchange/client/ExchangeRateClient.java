package pl.blu911.currencyexchange.client;

import org.json.*;
import pl.blu911.currencyexchange.model.ExchangeRate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ExchangeRateClient {
    private static final String ALPHA_ADVANTAGE_URI = "https://www.alphavantage.co/query?function=";
    private static final String API_KEY = "F41YX3ZBH15DU6BK";
    private static final String HISTORICAL_VALUES = "FX_DAILY";
    private static final String REALTIME_VALUES = "CURRENCY_EXCHANGE_RATE";

    public static ExchangeRate getRealTimeExchangeRate(String fromCurrency, String toCurrency) {

        String jsonString = HttpClient.getStringFromUri(
                String.format("%s%s&from_currency=%s&to_currency=%s&apikey=%s",
                        ALPHA_ADVANTAGE_URI,
                        REALTIME_VALUES,
                        fromCurrency,
                        toCurrency,
                        API_KEY));

        JSONObject jsonObject = new JSONObject(jsonString);

        boolean note = jsonObject.has("Note");
        boolean error = jsonObject.has("Error Message");

        if (!note && !error) {
            BigDecimal exchangeRate = new BigDecimal(jsonObject
                    .getJSONObject("Realtime Currency Exchange Rate")
                    .getString("5. Exchange Rate"));

            LocalDate date = LocalDate.parse(jsonObject
                    .getJSONObject("Realtime Currency Exchange Rate")
                    .getString("6. Last Refreshed")
                    .substring(0, 10));

            return new ExchangeRate(fromCurrency, toCurrency, exchangeRate, date, null);
        } else if (note) {
            return new ExchangeRate(fromCurrency, toCurrency,null,null,
                                    jsonObject.getString("Note"));
        }
        return new ExchangeRate(fromCurrency, toCurrency,null,null,
                                jsonObject.getString("Error Message"));
    }

    public static List<ExchangeRate> getHistoricalExchangeRates(String fromCurrency,
                                                                String toCurrency,
                                                                String outputSize) {
        List<ExchangeRate> exchangeRates = new ArrayList<>();

        String jsonString = HttpClient.getStringFromUri(
                String.format("%s%s&from_symbol=%s&to_symbol=%s&outputsize=%s&apikey=%s",
                        ALPHA_ADVANTAGE_URI,
                        HISTORICAL_VALUES,
                        fromCurrency,
                        toCurrency,
                        outputSize,
                        API_KEY));

        JSONObject jsonObject = new JSONObject(jsonString);

        boolean note = jsonObject.has("Note");
        boolean error = jsonObject.has("Error Message");

        if (!note && !error) {
            Iterator<String> keys = jsonObject.getJSONObject("Time Series FX (Daily)").keys();

            while (keys.hasNext()) {
                String key = keys.next();

                if (!key.equals("") && !key.equals(" ")) {
                    LocalDate date = LocalDate.parse(key);
                    BigDecimal exchangeRate = new BigDecimal(jsonObject
                            .getJSONObject("Time Series FX (Daily)")
                            .getJSONObject(key)
                            .getString("4. close"));
                    exchangeRates.add(new ExchangeRate(fromCurrency, toCurrency, exchangeRate, date, null));
                }
            }

            exchangeRates.sort(Comparator.comparing(ExchangeRate::getDate));
            return exchangeRates;

        } else if (note) {
            exchangeRates.add(new ExchangeRate(fromCurrency, toCurrency,null,null,
                                                jsonObject.getString("Note")));
            return exchangeRates;
        }
        exchangeRates.add(new ExchangeRate(fromCurrency, toCurrency, null, null,
                                            jsonObject.getString("Error Message")));
        return exchangeRates;
    }
}
