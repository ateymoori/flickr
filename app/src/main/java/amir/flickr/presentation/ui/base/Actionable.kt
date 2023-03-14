package amir.flickr.presentation.ui.base

sealed interface Actionable {
    object NotStarted : Actionable
    object InProgress : Actionable
    object Success : Actionable
    data class Failed(val throwable: Throwable) : Actionable
}
