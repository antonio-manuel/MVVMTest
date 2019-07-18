package eu.antoniolopez.playground.di

import eu.antoniolopez.playground.presentation.viewmodel.MainViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

val appModule = Kodein.Module(name = "appModule") {
   bind<MainViewModel>() with provider { MainViewModel() }
}
