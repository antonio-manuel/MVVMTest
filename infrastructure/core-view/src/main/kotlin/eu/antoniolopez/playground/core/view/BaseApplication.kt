package eu.antoniolopez.playground.core.view

import android.app.Application
import android.os.StrictMode
import eu.antoniolopez.playground.core.di.setApplicationContext
import org.kodein.di.KodeinAware
import timber.log.Timber

abstract class BaseApplication : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
        setApplicationContext()
        setupStrictMode()
        setupTimber()
    }

    private fun setupStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
