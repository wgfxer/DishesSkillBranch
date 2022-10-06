package ru.skillbranch.sbdelivery.domain.filter

import io.reactivex.rxjava3.core.Single
import ru.skillbranch.sbdelivery.domain.entity.DishEntity
import ru.skillbranch.sbdelivery.repository.DishesRepositoryContract
import ru.skillbranch.sbdelivery.repository.error.EmptyDishesError

class CategoriesFilterUseCase(private val repository: DishesRepositoryContract) : CategoriesFilter {
    override fun categoryFilterDishes(categoryId: String): Single<List<DishEntity>> {
        return repository.getCachedDishes()
            .map { it.filterByCategory(categoryId) }
            .flatMap { if (it.isEmpty()) Single.error(EmptyDishesError("dsd")) else Single.just(it) }
    }
}

private fun List<DishEntity>.filterByCategory(categoryId: String) =
    if (categoryId.isEmpty()) this else this.filter { it.categoryId == categoryId }
