package eu.antoniolopez.playground.navigation.command.dashboard

import android.net.Uri
import eu.antoniolopez.playground.navigation.NavigationCommand
import eu.antoniolopez.playground.navigation.command.Command

val dashboardNavigationCommand: () -> NavigationCommand = {
    val command: NavigationCommand = { scheme ->
        val uri = Uri.Builder()
            .scheme(scheme)
            .authority("dashboard")
            .build()
        Command(uri = uri)
    }
    command
}
