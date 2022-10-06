package ru.skillbranch.sbdelivery.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.skillbranch.sbdelivery.core.BaseViewModel
import ru.skillbranch.sbdelivery.domain.SearchUseCase
import ru.skillbranch.sbdelivery.repository.error.EmptyDishesError
import ru.skillbranch.sbdelivery.repository.mapper.DishesMapper
import java.util.concurrent.TimeUnit

class SearchViewModel(
    private val useCase: SearchUseCase,
    private val mapper: DishesMapper
) : BaseViewModel() {
    private val action = MutableLiveData<SearchState>()
    val state: LiveData<SearchState>
        get() = action

    fun initState() {
        useCase.getDishes()
            .doOnSubscribe { action.value = SearchState.Loading }
            .map { dishes -> mapper.mapDtoToState(dishes) }
            .subscribe({
                val newState = SearchState.Result(it)
                action.value = newState
            }, {
                it.printStackTrace()
                action.value = SearchState.Error(it.message.orEmpty())
            }).track()
    }

    fun setSearchEvent(searchEvent: Observable<String>) {
        searchEvent
            .doOnNext { action.value = SearchState.Loading }
            .debounce(800L, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .switchMap { useCase.findDishesByName(it) }
            .map { mapper.mapDtoToState(it) }
            .observeOn(AndroidSchedulers.mainThread())
            //.flatMap { if (it.isEmpty()) Observable.error(EmptyDishesError()) else Observable.just(it) }
            .subscribe({
                val newState = SearchState.Result(it)
                action.value = newState
            }, {
                action.value = SearchState.Error(it.message.orEmpty())
                it.printStackTrace()
            }).track()

    }

}