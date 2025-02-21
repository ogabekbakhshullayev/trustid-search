package uz.aigroup.trustiddemo.screen.result

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ResultScreenModel : StateScreenModel<ResultState>(ResultState()) {

    private val stateData = MutableStateFlow(ResultState())

    fun onEvent(event: ResultEvent) {
        when (event) {
            is ResultEvent.Idle -> setstate()
            is ResultEvent.Search -> search()
        }
    }

    private fun setstate(
        loading: Boolean = false,
        errorMessage: String? = null,
        succeeded: Boolean = false,
    ) {
        stateData.update {
            it.copy(
                loading = loading,
                errorMessage = errorMessage,
                succeeded = succeeded
            )
        }
    }

    private fun search() {
        mutableState.update { it.copy(loading = true) }

    }
}