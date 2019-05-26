package pl.blu911.currencyexchange.search;

import lombok.Data;
import pl.blu911.currencyexchange.exchangerate.ExchangeRate;

import java.util.List;

@Data
class SearchResponse {
    private String msg;
    private ExchangeRate realtimeRate;
    private List<List<ExchangeRate>> historicalRates;
}
