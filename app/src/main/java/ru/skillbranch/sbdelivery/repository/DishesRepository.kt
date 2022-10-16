package ru.skillbranch.sbdelivery.repository

import androidx.compose.ui.text.toLowerCase
import kotlinx.coroutines.delay
import ru.skillbranch.sbdelivery.data.db.dao.CartDao
import ru.skillbranch.sbdelivery.data.db.dao.DishesDao
import ru.skillbranch.sbdelivery.data.db.entity.CartItemPersist
import ru.skillbranch.sbdelivery.data.network.RestService
import ru.skillbranch.sbdelivery.data.network.res.DishRes
import ru.skillbranch.sbdelivery.data.toDishItem
import ru.skillbranch.sbdelivery.data.toDishPersist
import ru.skillbranch.sbdelivery.screens.dishes.data.DishItem
import javax.inject.Inject

interface IDishesRepository {
    suspend fun searchDishes(newInput: String): List<DishItem>
    suspend fun isEmptyDishes(): Boolean
    suspend fun syncDishes()
    suspend fun findDishes(): List<DishItem>
    suspend fun findSuggestions(query: String): Map<String, Int>
    suspend fun addDishToCart(id: String)
    suspend fun removeDishFromCart(dishId: String)
    suspend fun cartCount(): Int
}

class DishesRepository @Inject constructor(
    private val api: RestService,
    private val dishesDao: DishesDao,
    private val cartDao: CartDao
) : IDishesRepository {
    override suspend fun searchDishes(query: String): List<DishItem> {
        return if (query.isEmpty()) findDishes()
        else dishesDao.findDishesFrom(query)
            .map { it.toDishItem() }
    }

    override suspend fun isEmptyDishes(): Boolean  {
        return dishesDao.dishesCounts() == 0
    }

    override suspend fun syncDishes() {
        val dishes = mutableListOf<DishRes>()
        var offset = 0
        while (true) {
            val res = api.getDishes(offset * 10, 10)
            if (res.isSuccessful) {
                offset++
                dishes.addAll(res.body()!!)
            } else break
        }
        dishesDao.insertDishes(dishes.map { it.toDishPersist() })
    }

    override suspend fun findDishes(): List<DishItem> =
        dishesDao.findAllDishes().map { it.toDishItem() }

    override suspend fun findSuggestions(query: String): Map<String, Int> {
        val listOfAllIncomings = searchDishes(query).flatMap { dish ->
            dish.title.lowercase().split(" ").filter { it.contains(query, ignoreCase = true) }.map { it.onlyLetters }
        }
        return listOfAllIncomings.groupBy { it }.mapValues { it.value.size }
    }

    override suspend fun addDishToCart(id: String) {
        val count = cartDao.dishCount(id) ?: 0
        if (count > 0) cartDao.updateItemCount(id, count.inc())
        else cartDao.addItem(CartItemPersist(dishId = id))
    }

    override suspend fun removeDishFromCart(id: String) {
        val count = cartDao.dishCount(id) ?: 0
        if (count > 0) cartDao.decrementItemCount(id)
        else cartDao.removeItem(id)
    }

    override suspend fun cartCount(): Int = cartDao.cartCount() ?: 0
}

val String.onlyLetters get() = this.toCharArray()
    .filter { it.isLetterOrDigit() }
    .joinToString(separator = "")