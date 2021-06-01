package geekbrains.material.di

import geekbrains.material.di.modules.*
import geekbrains.material.mvp.presenter.*
import geekbrains.material.ui.fragment.*
import geekbrains.material.ui.activity.MainActivity

import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    ApiModule::class,
    PictureOfTheDayModule::class,
    NavigationModule::class,
    EarthGalleryModule::class
])

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(pictureOfTheDayPresenter: PictureOfTheDayPresenter)
    fun inject(wikiSearchPresenter: WikiSearchPresenter)
    fun inject(pictureOfTheDayFragment: PictureOfTheDayFragment)
    fun inject(wikiSearchFragment: WikiSearchFragment)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(earthGalleryFragment: EarthGalleryFragment)
    fun inject(earthGalleryPresenter: EarthGalleryPresenter)
    fun inject(earthPhotoFragment: EarthPhotoFragment)
    fun inject(earthPhotoPresenter: EarthPhotoPresenter)
}