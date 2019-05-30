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

        if (fromCurrency.equals("null") || toCurrency.equals("null")) {
            return ResponseEntity.badRequest().body(
                    LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                            + ": Please choose 2 currencies from the lists");
        } else if (fromCurrency.equals(toCurrency)) {
            return ResponseEntity.badRequest().body(
                    LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                            + ": Please select 2 different currencies");
        }

        SearchResponse result = new SearchResponse();

        ExchangeRate realTimeRate = exchangeRateService.getRealTimeRate(fromCurrency, toCurrency);
        if (realTimeRate == null) {
            return ResponseEntity.badRequest().body(
                    LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                            + ": Five attempts per minute exceeded");
        }

        List<ExchangeRate> historicalRates = exchangeRateService.getHistoricalRates(fromCurrency, toCurrency);
        if (historicalRates.get(0).getExchangeRate() == null) {
            if (historicalRates.get(0).getApiNote().contains("Invalid API call")) {
                return ResponseEntity.badRequest().body(
                        LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                                + ": There are no historical data for these currencies");
            }
            return ResponseEntity.badRequest().body(
                    LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                            + ": Five attempts per minute exceeded");
        }
        List<List<ExchangeRate>> historicalRatesInPeriods = exchangeRateService.getHistoricalRatesInPeriods(historicalRates);

        result.setMsg("Success");
        result.setRealtimeRate(realTimeRate);
        result.setHistoricalRates(historicalRatesInPeriods);
        return ResponseEntity.ok(result);
    }
}
