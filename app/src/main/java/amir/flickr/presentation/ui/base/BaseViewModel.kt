package amir.flickr.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface ViewState

interface ViewAction

interface ViewEffect

abstract class BaseViewModel<Action : ViewAction, UiState : ViewState, Effect : ViewEffect>(private val initialState: UiState) :
    ViewModel() {

    private val _viewState: MutableStateFlow<UiState> = MutableStateFlow(initialState)
    private val _effect: MutableSharedFlow<Effect> = MutableSharedFlow()

    val effect: SharedFlow<Effect>
        get() = _effect
    val viewState: StateFlow<UiState> = _viewState

    protected fun setState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    fun launchEffect(effect: Effect) = viewModelScope.launch {
        _effect.emit(effect)
    }

    abstract fun dispatch(action: Action)
}