package com.ams.khdmaEmployeeId

import android.app.Application
import android.os.StrictMode
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.defaultModule

@Suppress("unused")
class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        this.initAppPolices()
        this.initKoin()
    }

    private fun initAppPolices() {
        if (BuildConfig.DEBUG) {
            val threadPolices = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(threadPolices)
        }
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(defaultModule, apisModule)

        }
    }
}