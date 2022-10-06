package ru.skillbranch.sbdelivery.domain.filter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import ru.skillbranch.sbdelivery.MockDataHolder
import ru.skillbranch.sbdelivery.repository.DishesRepositoryContract
import ru.skillbranch.sbdelivery.repository.error.EmptyDishesError

class CategoriesFilterUseCaseTest {

    private val repository = mock<DishesRepositoryContract>()
    private lateinit var useCase: CategoriesFilterUseCase

    @Before
    fun setUp() {
        useCase = CategoriesFilterUseCase(repository)
    }

    @Test
    fun `when send categoryId should return filter list in categoryIds list`() {
        // given
        whenever(repository.getCachedDishes()).thenReturn(
            Single.just(
                MockDataHolder.listDishes
            )
        )
        val targetCategoryId = "5ed8da011f071c00465b1fe4"
        val correctDishes = MockDataHolder.listDishes.filter { it.categoryId == targetCategoryId }
        // when
        useCase.categoryFilterDishes(targetCategoryId)
            .test()
            .assertResult(correctDishes)
        // then
        verify(repository).getCachedDishes()
    }

    @Test
    fun `when empty categoryId should return full list DishesEntity`() {
        // given
        whenever(repository.getCachedDishes()).thenReturn(
            Single.just(
                MockDataHolder.listDishes
            )
        )
        val targetCategoryId = ""
        // when
        useCase.categoryFilterDishes(targetCategoryId)
            .test()
            .assertResult(MockDataHolder.listDishes)
        // then
        verify(repository).getCachedDishes()
    }

    @Test
    fun `when send categoryId should filter empty list throw EmptyDishesError `() {
        // given
        whenever(repository.getCachedDishes()).thenReturn(
            Single.just(
                MockDataHolder.listDishes
            )
        )
        val targetCategoryId = "5ed8da011f071c09565b1fe4"
        val correctError = EmptyDishesError::class.java
        // when
        useCase.categoryFilterDishes(targetCategoryId)
            .test()
            .assertError(correctError)
        // then
        verify(repository).getCachedDishes()
    }


}