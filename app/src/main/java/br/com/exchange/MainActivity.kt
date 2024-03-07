package br.com.exchange

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.exchange.feature.currency.domain.Currency
import br.com.exchange.feature.currency.presentation.CurrencyViewModel
import br.com.exchange.feature.currency.presentation.CurrencyViewState
import br.com.exchange.ui.theme.ExchangeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CurrencyScreen()
                }
            }
        }
    }
}

@Composable
fun CurrencyScreen(viewModel: CurrencyViewModel = viewModel()) {
    val currencyViewState by viewModel.currencyViewState.collectAsStateWithLifecycle(CurrencyViewState.Loading)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (currencyViewState) {
            is CurrencyViewState.Success -> showCurrencyData((currencyViewState as CurrencyViewState.Success).currencyList)
            is CurrencyViewState.Loading -> showLoading()
            is CurrencyViewState.Error -> showFailDialog()
        }
    }
}

@Composable
fun showFailDialog() {
    Text(text = "Error!")
}

@Composable
fun showLoading() {
    Text(text = "Loading...")
}

@Composable
fun showCurrencyData(currencyList: List<Currency>) {
    currencyList.forEach {
        Column() {
            Text(text = it.tag)
            Text(text = it.amount)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExchangeTheme {
        CurrencyScreen()
    }
}
