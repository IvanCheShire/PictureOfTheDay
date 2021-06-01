package geekbrains.material.ui

import android.app.Application
import geekbrains.material.di.AppComponent
import geekbrains.material.di.DaggerAppComponent
import geekbrains.material.di.modules.AppModule

class App: Application() {

    companion object {
        lateinit var instance: App
    }
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent =  DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}