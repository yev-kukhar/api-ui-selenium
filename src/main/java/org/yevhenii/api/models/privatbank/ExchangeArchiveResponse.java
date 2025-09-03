package org.yevhenii.api.models.privatbank;

import lombok.Data;

import java.util.List;

@Data
public class ExchangeArchiveResponse {
    private String date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;
    private List<ExchangeRate> exchangeRate;
}
