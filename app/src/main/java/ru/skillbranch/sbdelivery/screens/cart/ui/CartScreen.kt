package ru.skillbranch.sbdelivery.screens.cart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.skillbranch.sbdelivery.screens.cart.logic.CartFeature
import ru.skillbranch.sbdelivery.screens.cart.data.CartUiState
import ru.skillbranch.sbdelivery.screens.cart.data.ConfirmDialogState

@Composable
fun CartScreen(state: CartFeature.State, accept: (CartFeature.Msg) -> Unit) {
    when (state.list) {
        is CartUiState.Value -> {
            Column() {
                LazyColumn(
                    contentPadding = PaddingValues(0.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    content = {
                        val items = state.list.dishes
                        items(items = items, key = { it.id }) {
                            CartListItem(it,
                                onProductClick = { dishId: String, title: String -> accept(CartFeature.Msg.ClickOnDish(dishId, title))},
                                onIncrement = { dishId -> accept(CartFeature.Msg.IncrementCount(dishId))},
                                onDecrement = { dishId -> accept(CartFeature.Msg.DecrementCount(dishId))},
                                onRemove = { dishId, title -> accept(CartFeature.Msg.RemoveFromCart(dishId)) }
                            )
                        }

                    },
                    modifier = Modifier.weight(1f)
                )
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row() {
                        val total = state.list.dishes.sumBy { it.count * it.price }
                        Text(
                            "Итого",
                            fontSize = 24.sp,
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            "$total Р",
                            fontSize = 24.sp,
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colors.secondary
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = { accept(CartFeature.Msg.SendOrder(state.list.dishes.associateBy { it.title }.mapValues { it.value.count })) },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.secondary,
                            contentColor = MaterialTheme.colors.onSecondary
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Оформить заказ", style = TextStyle(fontWeight = FontWeight.Bold))
                    }
                }
            }

        }
        is CartUiState.Empty -> Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Пока ничего нет")
        }

        is CartUiState.Loading -> Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(color = MaterialTheme.colors.secondary)
        }
    }

    if (state.confirmDialog is ConfirmDialogState.Show) {
        AlertDialog(
            onDismissRequest = {  accept(CartFeature.Msg.HideConfirm)  },
            backgroundColor = Color.White,
            contentColor = MaterialTheme.colors.primary,
            title = { Text(text = "Вы уверены?") },
            text = { Text(text = "Вы точно хотите удалить ${state.confirmDialog.title} из корзины") },
            buttons = {
                Row {
                    TextButton(
                        onClick = { accept(CartFeature.Msg.ShowConfirm(state.confirmDialog.id, state.confirmDialog.title))  },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Нет", color = MaterialTheme.colors.secondary)
                    }
                    TextButton(
                        onClick = { accept(CartFeature.Msg.RemoveFromCart(state.confirmDialog.id))  },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Да", color = MaterialTheme.colors.secondary)
                    }
                }

            }
        )
    }
}