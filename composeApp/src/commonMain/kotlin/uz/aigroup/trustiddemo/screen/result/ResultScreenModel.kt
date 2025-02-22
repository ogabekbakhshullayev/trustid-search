package uz.aigroup.trustiddemo.screen.result

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import uz.aigroup.trustiddemo.data.remote.request.SearchRequest
import uz.aigroup.trustiddemo.data.remote.util.onFailure
import uz.aigroup.trustiddemo.data.remote.util.onSuccess
import uz.aigroup.trustiddemo.data.repository.SearchRepository
import uz.aigroup.trustiddemo.platform.SharedImage

class ResultScreenModel : StateScreenModel<ResultState>(ResultState()), KoinComponent {

    private val repository by inject<SearchRepository>()

    fun onEvent(event: ResultEvent) {
        when (event) {
            is ResultEvent.Search -> search(event.groupId, event.sharedImage)
            is ResultEvent.Idle -> setState()
        }
    }

    private fun search(
        groupId: String,
        sharedImage: SharedImage,
    ) {
        setState(loading = true)

        screenModelScope.launch {
            val base64 = sharedImage.toBase64()
            val request = SearchRequest(
                cameraPhoto = base64,
                groupId = groupId,
            )

            repository.search(request).collectLatest {
                it onSuccess {
                    searchResult(data?.taskId)
                } onFailure {
                    setState(errorMessage = message)
                }
            }
        }
    }

    private fun searchResult(taskId: String?) {
        setState(loading = true)

        screenModelScope.launch {
            repository.searchResult(taskId).collectLatest {
                it onSuccess {
                    if (statusCode == 202) {
                        searchResult(taskId)
                    } else if (data?.state?.code == 1) {
                        setState(result = data.state.message)
                    } else {
                        setState(errorMessage = data?.state?.message)
                    }
                } onFailure {
                    setState(errorMessage = message)
                }
            }
        }
    }

    private fun setState(
        loading: Boolean = false,
        errorMessage: String? = null,
        result: String? = null,
    ) {
        mutableState.update {
            it.copy(
                loading = loading,
                errorMessage = errorMessage,
                result = result
            )
        }
    }
}