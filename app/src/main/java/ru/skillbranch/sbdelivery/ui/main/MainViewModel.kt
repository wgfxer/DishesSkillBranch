package ru.skillbranch.sbdelivery.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.skillbranch.sbdelivery.core.BaseViewModel
import ru.skillbranch.sbdelivery.core.adapter.ProductItemState
import ru.skillbranch.sbdelivery.core.notifier.BasketNotifier
import ru.skillbranch.sbdelivery.core.notifier.event.BasketEvent
import ru.skillbranch.sbdelivery.domain.filter.CategoriesFilterUseCase
import ru.skillbranch.sbdelivery.repository.DishesRepositoryContract
import ru.skillbranch.sbdelivery.repository.error.EmptyDishesError
import ru.skillbranch.sbdelivery.repository.mapper.CategoriesMapper
import ru.skillbranch.sbdelivery.repository.mapper.DishesMapper

class MainViewModel(
    private val repository: DishesRepositoryContract,
    private val dishesMapper: DishesMapper,
    private val categoriesMapper: CategoriesMapper,
    private val notifier: BasketNotifier,
    private val categoriesFilterUseCase: CategoriesFilterUseCase
) : BaseViewModel() {

    private val defaultState = MainState.Loader
    private val action = MutableLiveData<MainState>()
    val state: LiveData<MainState>
        get() = action

    init {
        loadDishes()
    }

    fun loadDishes() {
        repository.getDishes()
            .doOnSubscribe { action.value = defaultState }
            .flatMap { dishes -> repository.getCategories().map { it to dishes } }
            .map { categoriesMapper.mapDtoToState(it.first) to dishesMapper.mapDtoToState(it.second) }
            .subscribe({
                val newState = MainState.Result(it.second, it.first)
                action.value = newState
            }, {
                if (it is EmptyDishesError) {
                    action.value = MainState.Error(it.messageDishes, it)
                } else {
                    action.value = MainState.Error("Что то пошло не по плану", it)
                }
                it.printStackTrace()
            }).track()
    }

    fun handleAddBasket(item: ProductItemState) {
        notifier.putDishes(BasketEvent.AddDish(item.id, item.title, item.price))
    }

    fun handleCategoryClick(categoryId: String) {
        categoriesFilterUseCase.categoryFilterDishes(categoryId)
            .map { dishesMapper.mapDtoToState(it) }
            .subscribe({ result ->
                val currentState = action.value
                if (currentState is MainState.Result) {
                    action.value = currentState.copy(productItems = result)
                }
            }, {})
    }
}