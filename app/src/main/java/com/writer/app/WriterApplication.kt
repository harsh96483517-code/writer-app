package com.writer.app

import android.app.Application
import com.writer.app.di.AppContainer

class WriterApplication : Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
