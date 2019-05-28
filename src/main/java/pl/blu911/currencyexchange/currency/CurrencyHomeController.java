package pl.blu911.currencyexchange.currency;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CurrencyHomeController {
    private final CurrencyServiceImpl currenciesService;

    public CurrencyHomeController(CurrencyServiceImpl currenciesService) {
        this.currenciesService = currenciesService;
    }

    @ModelAttribute("currencies")
    public List<Currency> currenciesList() {
        return currenciesService.getAllCurrencies();
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

}