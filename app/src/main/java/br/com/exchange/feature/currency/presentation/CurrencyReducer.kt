package br.com.exchange.feature.currency.presentation

import br.com.exchange.feature.currency.domain.Currency
import com.github.oxeanbits.redukt.actions.Action
import com.github.oxeanbits.redukt.reducers.Reducer

class CurrencyReducer: Reducer<CurrencyViewState> {
    override fun reduce(state: CurrencyViewState, action: Action<*>): CurrencyViewState {
        return when (action.name) {
            CurrencyActions.ShowData.name ->{
                CurrencyViewState.Success(action.payload as? List<Currency> ?: emptyList())
            }
            CurrencyActions.Refresh.name,
            CurrencyActions.Search.name -> {
                CurrencyViewState.Loading
            } else -> {
                CurrencyViewState.Error
            }
        }
    }
}

enum class CurrencyActions{
    Refresh,
    Search,
    ShowData,
    ShowError
}