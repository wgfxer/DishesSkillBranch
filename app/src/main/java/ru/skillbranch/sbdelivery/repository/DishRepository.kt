package ru.skillbranch.sbdelivery.repository

import ru.skillbranch.sbdelivery.data.db.dao.CartDao
import ru.skillbranch.sbdelivery.data.db.dao.DishesDao
import ru.skillbranch.sbdelivery.data.db.entity.CartItemPersist
import ru.skillbranch.sbdelivery.data.network.RestService
import ru.skillbranch.sbdelivery.data.network.req.ReviewReq
import ru.skillbranch.sbdelivery.data.network.res.DishRes
import ru.skillbranch.sbdelivery.data.network.res.ReviewRes
import ru.skillbranch.sbdelivery.data.toDishContent
import ru.skillbranch.sbdelivery.data.toDishPersist
import ru.skillbranch.sbdelivery.screens.dish.data.DishContent
import java.io.IOException
import javax.inject.Inject

interface IDishRepository {
    suspend fun findDish(id: String): DishContent
    suspend fun addToCart(id: String, count: Int)
    suspend fun cartCount(): Int
    suspend fun loadReviews(dishId: String): List<ReviewRes>
    suspend fun sendReview(id: String, rating: Int, review: String): ReviewRes
}

class DishRepository @Inject constructor(
    private val api: RestService,
    private val dishesDao: DishesDao,
    private val cartDao: CartDao,
) : IDishRepository {
    override suspend fun findDish(id: String): DishContent = dishesDao.findDish(id).toDishContent()

    override suspend fun addToCart(id: String, count: Int) {
        cartDao.addItem(CartItemPersist(dishId = id, count = count))
    }

    override suspend fun cartCount(): Int {
        return cartDao.cartCount() ?: 0
    }

    override suspend fun loadReviews(dishId: String): List<ReviewRes> {
        val reviews = mutableListOf<ReviewRes>()
        var offset = 0
        while (true) {
            val res = api.getReviews(dishId, offset * 10, 10)
            if (res.isSuccessful) {
                offset++
                reviews.addAll(res.body()!!)
            } else break
        }
        return reviews
    }

    override suspend fun sendReview(id: String, rating: Int, review: String): ReviewRes {
        return api.sendReview(id, ReviewReq(rating, review))
    }
}