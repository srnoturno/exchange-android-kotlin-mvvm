package br.com.exchange.feature.currency.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.exchange.R
import br.com.exchange.feature.currency.domain.Currency
import br.com.exchange.ui.theme.ExchangeTheme

@Composable
fun CurrencyScreen(viewModel: CurrencyViewModel = viewModel()) {
    val viewState by viewModel.currencyViewState.collectAsStateWithLifecycle()
    val scrollState = rememberLazyGridState()

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ) {
            Column {
                Text(
                    text = "Exchange Rates",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start
                )
                SelectCurrency(currencyList = viewModel.currencyList) {
                    viewModel.setNewBaseCurrency(it)
                }
            }
        }

        if (viewState is CurrencyViewState.Success) {
            val currencyList = (viewState as CurrencyViewState.Success).currencyList
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = scrollState,
                modifier = Modifier.weight(1f)
            ) {
                items(currencyList) { currency ->
                    CurrencyCard(currency = currency)
                }
            }
        }

        if (viewState is CurrencyViewState.Loading) {
            ShowLoading()
        }

        if (viewState is CurrencyViewState.Error) {
            ShowFailDialog()
        }

        RefreshButton(onClick = {
            viewModel.refreshCurrencyData()
        })
    }
}

@Composable
fun CurrencyCard(
    currency: Currency,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth(0.45f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = currency.tag, style = MaterialTheme.typography.bodyLarge)
            Text(text = currency.amount, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ShowFailDialog() {
    Text(text = "Error!")
}

@Composable
fun ShowLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun RefreshButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = "Refresh")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ExchangeTheme {
        CurrencyScreen()
    }
}

@Composable
fun SelectCurrency(currencyList: List<String>, currencySelector: (currency: String) -> Unit) {
    var currentCurrency by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember { mutableStateOf(currencyList.first()) }

    Column(

        modifier = Modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(15.dp))
            .clickable(
                onClick = { currentCurrency = !currentCurrency }
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(text = "Selecionado: $selectedItem", color = Color.Black)
            Arrow()
        }
        DropdownMenu(
            expanded = currentCurrency,
            onDismissRequest = { currentCurrency = false }
        ) {
            currencyList.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        selectedItem = item
                        currentCurrency = !currentCurrency
                        currencySelector.invoke(item)
                    })
            }
        }
    }
}

@Composable
fun Arrow() {
    Image(
        painter = painterResource(id = R.drawable.arrow_down),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
    )
}