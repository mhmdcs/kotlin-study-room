package random_misc

sealed class UiState {
    object Loading : UiState()
    data class Success(val successMessage: String) : UiState()
    data class Error(val errorMessage: Throwable?) : UiState()
}


fun observeUiStates(uiState: UiState) = when(uiState) {
    UiState.Loading -> println("Loading...Please wait.")
    is UiState.Success -> println(uiState.successMessage)
    is UiState.Error -> println(uiState.errorMessage)
}

fun main() {
    observeUiStates(UiState.Loading)
    observeUiStates(UiState.Success("The Ui has returned success state"))
}


//https://stackoverflow.com/questions/68774084/how-to-use-sealed-classes-in-android-using-kotlin