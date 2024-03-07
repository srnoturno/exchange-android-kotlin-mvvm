package br.com.exchange.feature.currency.presentation

import app.cash.turbine.test
import br.com.exchange.feature.currency.domain.Currency
import br.com.exchange.feature.currency.domain.CurrencyUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class CurrencyViewModelTest {

    @get:Rule
    private lateinit var currencyUseCase: CurrencyUseCase
    private lateinit var currencyViewModel: CurrencyViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        currencyUseCase = mockk(relaxed = true)
        currencyViewModel = CurrencyViewModel(currencyUseCase)
    }

    @Test
    fun `fetch should update currencyViewState with Success`() = runTest {
        val fakeResponse = mockk<List<Currency>>(relaxed = true)
        coEvery { currencyUseCase.getCurrency("brl") } returns fakeResponse
        currencyViewModel.fetch("brl")
        currencyViewModel.currencyViewState.test {
            val firstItem = awaitItem()
            assertTrue { firstItem is CurrencyViewState.Success }
        }
    }

    @Test
    fun `fetch should update currencyViewState with Error`() = runTest {
        val fakeResponse = Exception()
        coEvery { currencyUseCase.getCurrency("brl") } throws fakeResponse
        currencyViewModel.fetch("brl")
        currencyViewModel.currencyViewState.test {
            val firstItem = awaitItem()
            assertTrue { firstItem is CurrencyViewState.Error }
        }
    }
}
