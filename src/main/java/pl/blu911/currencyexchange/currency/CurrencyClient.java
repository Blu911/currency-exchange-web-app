package pl.blu911.currencyexchange.currency;

import org.json.JSONObject;
import pl.blu911.currencyexchange.client.HttpClient;

import java.util.*;

public class CurrencyClient {

    private static final String CURRENCIES_URI = "https://openexchangerates.org/api/currencies.json";

    public static List<Currency> getCurrencies() {
        List<Currency> currencyList = new ArrayList<>();

        String jsonString = HttpClient.getStringFromUri(CURRENCIES_URI);

        JSONObject jsonObject = new JSONObject(jsonString);
        Iterator<String> keys = jsonObject.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            if (!key.equals("") && !key.equals(" "))
                currencyList.add(new Currency(key, jsonObject.getString(key)));
        }

        currencyList.sort(Comparator.comparing(Currency::getCode));
        return currencyList;
    }
}
