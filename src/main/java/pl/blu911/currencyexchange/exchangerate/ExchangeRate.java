package pl.blu911.currencyexchange.exchangerate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRate {
    private BigDecimal exchangeRate;
    private LocalDate date;
    private String apiNote;
}
