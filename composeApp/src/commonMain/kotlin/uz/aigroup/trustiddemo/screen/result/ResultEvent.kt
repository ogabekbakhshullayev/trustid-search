package uz.aigroup.trustiddemo.screen.result

sealed interface ResultEvent {
    data object Idle : ResultEvent
    data object Search: ResultEvent
}