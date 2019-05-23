package pl.blu911.currencyexchange.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal exchangeRate;
    private LocalDate date;
}
