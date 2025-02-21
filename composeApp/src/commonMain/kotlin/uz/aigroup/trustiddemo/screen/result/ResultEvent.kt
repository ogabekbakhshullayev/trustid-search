package uz.aigroup.trustiddemo.screen.result

import uz.aigroup.trustiddemo.platform.SharedImage

sealed interface ResultEvent {
    data class Search(
        val groupId: String,
        val sharedImage: SharedImage,
    ) : ResultEvent

    data object Idle : ResultEvent
}