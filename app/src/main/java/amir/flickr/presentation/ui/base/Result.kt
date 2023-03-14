package amir.flickr.presentation.ui.base

sealed interface Loadable<out T>

data class Loaded<T>(val data: T) : Loadable<T>
data class Failed<T>(val message: String) : Loadable<T>
object Loading : Loadable<Nothing>
object InitialState : Loadable<Nothing>
