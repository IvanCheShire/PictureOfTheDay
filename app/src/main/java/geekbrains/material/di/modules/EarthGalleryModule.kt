package geekbrains.material.di.modules

import geekbrains.material.mvp.model.api.IEarthGallerySource
import geekbrains.material.mvp.model.repo.IEarthGalleryRepo
import geekbrains.material.mvp.model.repo.retrofit.RetrofitEarthGalleryRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EarthGalleryModule {

    @Singleton
    @Provides
    fun earthGalleryRepo(api: IEarthGallerySource): IEarthGalleryRepo =
            RetrofitEarthGalleryRepo(api)
}