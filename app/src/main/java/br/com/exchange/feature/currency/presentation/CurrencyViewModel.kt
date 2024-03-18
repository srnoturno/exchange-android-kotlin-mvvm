package br.com.exchange.feature.currency.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.exchange.feature.currency.domain.CurrencyUseCase
import com.github.oxeanbits.redukt.Redukt
import com.github.oxeanbits.redukt.actions.Action
import com.github.oxeanbits.redukt.states.StateListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CurrencyViewModel(private val currencyUseCase: CurrencyUseCase = CurrencyUseCase()) :
    ViewModel() {

    private val currencyReduce = Redukt<CurrencyViewState>(CurrencyViewState.Loading)
    private val _currencyViewState = MutableStateFlow<CurrencyViewState>(CurrencyViewState.Loading)
    val currencyViewState = _currencyViewState.asStateFlow()

    init {
        currencyReduce.reducers["currencyReduce"] = CurrencyReducer()
        fetch("brl")

        currencyReduce.listeners.add(object: StateListener<CurrencyViewState> {

            override fun hasChanged(
                newState: CurrencyViewState,
                oldState: CurrencyViewState
            ): Boolean {
                return newState != oldState
            }

            override fun onChanged(state: CurrencyViewState) {
                _currencyViewState.update { state }
            }
        })
    }

    fun fetch(currency: String) {
        currencyReduce.dispatch(Action<Any>(CurrencyActions.Refresh.name) )
        viewModelScope.launch {
            try {
                val response = currencyUseCase.getCurrency(currency)
                currencyReduce.dispatch(Action(CurrencyActions.ShowData.name, response) )

            } catch (exception: Exception) {
                currencyReduce.dispatch(Action<Any>(CurrencyActions.ShowError.name) )
            }
        }
    }
}