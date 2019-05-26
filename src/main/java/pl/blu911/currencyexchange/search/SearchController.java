package pl.blu911.currencyexchange.search;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.blu911.currencyexchange.exchangerate.ExchangeRate;
import pl.blu911.currencyexchange.exchangerate.ExchangeRateServiceImpl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
public class SearchController {

    private final ExchangeRateServiceImpl exchangeRateService;

    public SearchController(ExchangeRateServiceImpl exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> getSearchResult(@RequestParam String fromCurrency,
                                             @RequestParam String toCurrency) {
        SearchResponse result = new SearchResponse();

        ExchangeRate realTimeRate = exchangeRateService.getRealTimeRate(fromCurrency, toCurrency);
        if (realTimeRate == null) {
            return ResponseEntity.badRequest().body(
                    LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                            + ": There is a connection problem with external API OR " +
                            "exceeded 5 attempts per minute");
        }

        List<ExchangeRate> historicalRates = exchangeRateService.getHistoricalRates(fromCurrency, toCurrency);
        if (historicalRates == null) {
            return ResponseEntity.badRequest().body(
                    LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                            + ": There is no historical data for these currencies OR " +
                            "exceeded 5 attempts per minute");
        }
        List<List<ExchangeRate>> historicalRatesInPeriods = exchangeRateService.getHistoricalRatesInPeriods(historicalRates);

        result.setMsg("Success");
        result.setRealtimeRate(realTimeRate);
        result.setHistoricalRates(historicalRatesInPeriods);
        return ResponseEntity.ok(result);
    }
}
