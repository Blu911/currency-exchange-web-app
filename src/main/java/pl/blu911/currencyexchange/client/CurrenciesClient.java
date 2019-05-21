package pl.blu911.currencyexchange.client;

import java.io.IOException;

public class CurrenciesClient {

    private static final String CURRENCIES_URI = "https://openexchangerates.org/api/currencies.json";

    public static String getFileContent() throws IOException {
        return HttpClient.getStringFromUri(CURRENCIES_URI);
    }
}
