package geekbrains.material.di.modules

import geekbrains.material.mvp.model.api.IPictureOfTheDaySource
import geekbrains.material.mvp.model.repo.IPictureOfTheDayRepo
import geekbrains.material.mvp.model.repo.retrofit.RetrofitPictureOfTheDayRepo
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class PictureOfTheDayModule {

    @Singleton
    @Provides
    fun pictureOfTheDayRepo(api: IPictureOfTheDaySource): IPictureOfTheDayRepo =
            RetrofitPictureOfTheDayRepo(api)
}