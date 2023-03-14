package amir.flickr.presentation.ui.screens.home

import amir.flickr.data.models.Photo
import amir.flickr.presentation.ui.base.Loadable
import amir.flickr.presentation.ui.base.ViewAction
import amir.flickr.presentation.ui.base.ViewEffect
import amir.flickr.presentation.ui.base.ViewState

interface HomeContract {

    sealed interface HomeAction : ViewAction {
        data class Search(val query: String) : HomeAction
        object ScrolledToEndOfTheList : HomeAction
    }

    sealed interface HomeEffect : ViewEffect {
        object NothingFound : HomeEffect
    }

    data class HomeState(
        var state: Loadable<List<Photo>>,
        val query: String = "",
        val currentPage: Int = 0
    ) : ViewState
}

