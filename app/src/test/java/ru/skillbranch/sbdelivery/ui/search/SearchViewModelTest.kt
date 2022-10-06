package ru.skillbranch.sbdelivery.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.ReplaySubject
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ru.skillbranch.sbdelivery.MockDataHolder
import ru.skillbranch.sbdelivery.RxImmediateSchedulerRule
import ru.skillbranch.sbdelivery.domain.SearchUseCase
import ru.skillbranch.sbdelivery.domain.entity.DishEntity
import ru.skillbranch.sbdelivery.repository.error.EmptyDishesError
import ru.skillbranch.sbdelivery.repository.mapper.DishesMapper

@RunWith(JUnit4::class)
class SearchViewModelTest {

    @Rule
    @JvmField
    var executorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private val mapper: DishesMapper = mock()
    private val useCase: SearchUseCase = mock()
    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        viewModel = SearchViewModel(useCase, mapper)
    }

    @Test
    fun `when loading and init should state Loading`() {
        val hotObserve: ReplaySubject<List<DishEntity>> = ReplaySubject.create()
        whenever(useCase.getDishes()).thenReturn(hotObserve.hide().single(listOf()))
        whenever(mapper.mapDtoToState(any())).thenReturn(MockDataHolder.searchStateList)
        viewModel.initState()
        Assertions.assertThat(viewModel.state.value).isEqualTo(SearchState.Loading)

        verify(useCase).getDishes()
    }

    @Test
    fun `when use case success data should value state in Result`() {
        whenever(useCase.getDishes()).thenReturn(Single.just(MockDataHolder.listDishes))
        whenever(mapper.mapDtoToState(any())).thenReturn(MockDataHolder.searchStateList)
        viewModel.initState()
        Assertions.assertThat(viewModel.state.value).isEqualTo(SearchState.Result(MockDataHolder.searchStateList))

        verify(useCase).getDishes()
        verify(mapper).mapDtoToState(any())
    }

    @Test
    fun `when use case error data should value return in SearchState Error`() {
        whenever(useCase.getDishes()).thenReturn(Single.error(EmptyDishesError("test error")))
        whenever(mapper.mapDtoToState(any())).thenReturn(MockDataHolder.searchStateList)
        viewModel.initState()
        Assertions.assertThat(viewModel.state.value).isEqualTo(SearchState.Error("test error"))

        verify(useCase).getDishes()
    }

    @Test
    fun `when search in dishes should return error use case show Error state`() {
        whenever(useCase.findDishesByName(any())).thenReturn(Observable.error(EmptyDishesError("test error")))
        whenever(mapper.mapDtoToState(any())).thenReturn(MockDataHolder.searchStateList)
        viewModel.setSearchEvent(Observable.just("Test"))
        Assertions.assertThat(viewModel.state.value).isEqualTo(SearchState.Error("test error"))
        verify(useCase).findDishesByName(any())
    }

    @Test
    fun `when search in dishes should return success show Result state`() {
        whenever(useCase.findDishesByName(any())).thenReturn(Observable.just(MockDataHolder.listDishes))
        whenever(mapper.mapDtoToState(any())).thenReturn(MockDataHolder.searchStateList)
        viewModel.setSearchEvent(Observable.just("Test"))
        Assertions.assertThat(viewModel.state.value).isEqualTo(SearchState.Result(MockDataHolder.searchStateList))

        verify(useCase).findDishesByName(any())
        verify(mapper).mapDtoToState(any())
    }
}