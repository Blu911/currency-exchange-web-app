package pl.blu911.currencyexchange.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    private String code;
    private String name;
}
