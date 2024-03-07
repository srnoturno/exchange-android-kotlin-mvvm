package br.com.exchange.plataform

import br.com.exchange.feature.currency.data.CurrencyResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ExchangeService {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getCurrency(currency: String): CurrencyResponse =
        client.get("https://open.er-api.com/v6/latest/$currency").body()

    companion object {
        val INSTANCE by lazy {
            ExchangeService()
        }
    }
}
