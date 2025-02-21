package uz.aigroup.trustiddemo.di

import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import uz.aigroup.trustiddemo.data.repository.SearchRepository
import uz.aigroup.trustiddemo.data.store.AppSettings

expect fun platformModule(): Module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        commonModule(),
        platformModule()
    )
}

// Called by iOS client
fun initKoin() = initKoin {}

fun commonModule() = module {
    singleOf(::AppSettings)
    singleOf(::SearchRepository)
}

fun reloadKoinModules() {
    unloadKoinModules(listOf(platformModule(), commonModule()))
    loadKoinModules(listOf(platformModule(), commonModule()))
}