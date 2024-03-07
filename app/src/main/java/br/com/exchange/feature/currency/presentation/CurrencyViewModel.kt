package br.com.exchange.feature.currency.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.exchange.feature.currency.domain.CurrencyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CurrencyViewModel(private val currencyUseCase: CurrencyUseCase = CurrencyUseCase()) :
    ViewModel() {
    init {
        fetch("brl")
    }

    private val _currencyViewState: MutableStateFlow<CurrencyViewState> =
        MutableStateFlow(CurrencyViewState.Loading)
    val currencyViewState = _currencyViewState.asStateFlow()

    fun fetch(currency: String) {
        viewModelScope.launch {
            val response = currencyUseCase.getCurrency(currency)
            _currencyViewState.update { CurrencyViewState.Success(response) }
        }
    }
}