package amir.flickr.presentation.ui.screens.home

import amir.flickr.data.models.Photo
import amir.flickr.domain.usecase.SearchUseCase
import amir.flickr.presentation.ui.base.BaseViewModel
import amir.flickr.presentation.ui.base.Failed
import amir.flickr.presentation.ui.base.InitialState
import amir.flickr.presentation.ui.base.Loaded
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : BaseViewModel<HomeContract.HomeAction, HomeContract.HomeState,
        HomeContract.HomeEffect>(HomeContract.HomeState(InitialState)) {

    private val list = mutableListOf<Photo>()

    private fun searchPhoto(query: String) {
        viewModelScope.launch {
            if (query != viewState.value.query) {
                list.clear()
                setState {
                    viewState.value.copy(query = query)
                }
            }

            try {
                val response = searchUseCase.invoke(query, viewState.value.currentPage)
                list.addAll(response.photoList)
                setState {
                    viewState.value.copy(
                        state = Loaded(
                            list.distinctBy { it.id }
                        )
                    )
                }
            } catch (e: Exception) {
                setState {
                    viewState.value.copy(state = Failed(e.message.toString()))
                }
            }

        }
    }

    override fun dispatch(action: HomeContract.HomeAction) {
        when (action) {
            is HomeContract.HomeAction.Search -> {
                setState {
                    viewState.value.copy(currentPage = 1)
                }
                searchPhoto(action.query)
            }
            HomeContract.HomeAction.ScrolledToEndOfTheList -> {
                setState {
                    viewState.value.copy(currentPage = currentPage + 1)
                }
                searchPhoto(viewState.value.query)
            }
        }
    }
}