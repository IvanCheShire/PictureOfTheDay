package geekbrains.material.di

import geekbrains.material.di.modules.ApiModule
import geekbrains.material.di.modules.AppModule
import geekbrains.material.di.modules.NavigationModule
import geekbrains.material.di.modules.PictureOfTheDayModule
import geekbrains.material.mvp.presenter.MainPresenter
import geekbrains.material.mvp.presenter.PictureOfTheDayPresenter
import geekbrains.material.mvp.presenter.WikiSearchPresenter
import geekbrains.material.ui.activity.MainActivity

import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    ApiModule::class,
    PictureOfTheDayModule::class,
    NavigationModule::class
])

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(pictureOfTheDayPresenter: PictureOfTheDayPresenter)
    fun inject(wikiSearchPresenter: WikiSearchPresenter)

}