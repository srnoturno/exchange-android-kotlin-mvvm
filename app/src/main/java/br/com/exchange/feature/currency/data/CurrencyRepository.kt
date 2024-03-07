package br.com.exchange.feature.currency.data

import br.com.exchange.plataform.ExchangeService

class CurrencyRepository(private val service: ExchangeService = ExchangeService.INSTANCE) {

    suspend fun getCurrency(currency: String): CurrencyResponse {
        return service.getCurrency(currency)
    }
}