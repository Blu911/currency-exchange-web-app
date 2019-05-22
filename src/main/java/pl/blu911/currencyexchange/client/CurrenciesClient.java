package pl.blu911.currencyexchange.client;

import com.codesnippets4all.json.parsers.JSONParser;
import com.codesnippets4all.json.parsers.JsonParserFactory;
import pl.blu911.currencyexchange.model.Currency;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CurrenciesClient {

    private static final String CURRENCIES_URI = "https://openexchangerates.org/api/currencies.json";

    public static List<Currency> getCurrencies() {
        String jsonString = HttpClient.getStringFromUri(CURRENCIES_URI);

        JsonParserFactory factory = JsonParserFactory.getInstance();
        JSONParser parser = factory.newJsonParser();
        Map jsonMap = parser.parseJson(jsonString);

        List<Currency> currencyList = new ArrayList<>();

        jsonMap.forEach((key, value) -> {
            currencyList.add(new Currency((String) key, (String) value));
        });

        currencyList.sort(Comparator.comparing(Currency::getCode));
        return currencyList;
    }
}
