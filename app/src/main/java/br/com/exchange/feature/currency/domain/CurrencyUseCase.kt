package br.com.exchange.feature.currency.domain

import br.com.exchange.feature.currency.data.CurrencyRepository
import br.com.exchange.feature.currency.data.CurrencyResponse

class CurrencyUseCase(private val currencyRepository: CurrencyRepository = CurrencyRepository()) {
    suspend fun getCurrency(currency: String): List<Currency> {
        return currencyRepository.getCurrency(currency).toDomain()
    }
}

private fun CurrencyResponse.toDomain(): List<Currency> {
    return listOf<Currency>(
        Currency(tag = "BRL", amount = this.rates.BRL.toString()),
        Currency(tag = "USD", amount = this.rates.USD.toString()),
        Currency(tag = "CNY", amount = this.rates.CNY.toString()),
        Currency(tag = "CAD", amount = this.rates.CAD.toString()),
        Currency(tag = "AUD", amount = this.rates.AUD.toString())
    )
}
