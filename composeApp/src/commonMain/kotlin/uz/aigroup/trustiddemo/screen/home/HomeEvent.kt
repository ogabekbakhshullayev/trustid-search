package uz.aigroup.trustiddemo.screen.home

sealed interface HomeEvent {
    data class ChangeGroupId(val groupId: String) : HomeEvent
}