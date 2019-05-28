package pl.blu911.currencyexchange.currency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);
    private final CurrencyClient currencyClient;

    public CurrencyServiceImpl(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    @Override
    public List<Currency> getAllCurrencies() {
        List<Currency> currencies = currencyClient.getCurrencies();
        if (currencies == null || currencies.isEmpty()) {
            LOGGER.error("Couldn't download currencies from https://openexchangerates.org/api/currencies.json");
            return null;
        }
        return currencies;
    }

}
