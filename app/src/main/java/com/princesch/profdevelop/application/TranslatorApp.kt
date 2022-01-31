package com.princesch.profdevelop.application

import android.app.Application
import com.princesch.profdevelop.di.application
import com.princesch.profdevelop.di.mainScreen
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}
