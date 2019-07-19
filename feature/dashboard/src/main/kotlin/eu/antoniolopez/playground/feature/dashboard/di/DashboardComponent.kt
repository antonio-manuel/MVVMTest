package eu.antoniolopez.playground.feature.dashboard.di

import eu.antoniolopez.playground.core.di.coreComponent
import eu.antoniolopez.playground.navigation.di.navigationModule
import org.kodein.di.conf.ConfigurableKodein

val dashboardComponent = ConfigurableKodein(mutable = true).apply {
    this.addExtend(coreComponent)
    this.addImport(dashboardModule)
    this.addImport(navigationModule)
}