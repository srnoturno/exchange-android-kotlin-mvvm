package br.com.exchange.feature.currency.presentation

import br.com.exchange.feature.currency.domain.Currency

sealed class CurrencyViewState {
    class Success(val currencyList: List<Currency>) : CurrencyViewState()
    object Loading : CurrencyViewState()
    object Error : CurrencyViewState()
}