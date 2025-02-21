package uz.aigroup.trustiddemo.screen.home

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update

class HomeScreenModel : StateScreenModel<HomeState>(HomeState()) {

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ChangeGroupId -> changeGroupId(event.groupId)
        }
    }

    private fun changeGroupId(groupId: String) {
        mutableState.update {
            it.copy(
                groupId = groupId,
                enabled = groupId.isNotBlank(),
            )
        }
    }
}