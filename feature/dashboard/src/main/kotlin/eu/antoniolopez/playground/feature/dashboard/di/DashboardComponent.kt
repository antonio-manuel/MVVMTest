package eu.antoniolopez.playground.feature.dashboard.di

import eu.antoniolopez.playground.core.di.coreComponent
import org.kodein.di.conf.ConfigurableKodein

val dashboardComponent: ConfigurableKodein = ConfigurableKodein(mutable = true).apply {
    this.addExtend(coreComponent)
    this.addImport(dashboardModule)
}
