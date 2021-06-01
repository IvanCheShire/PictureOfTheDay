package geekbrains.material.mvp.presenter

import geekbrains.material.mvp.model.entity.EarthPhotoServerResponse
import geekbrains.material.mvp.model.repo.IEarthGalleryRepo
import io.reactivex.rxjava3.core.Scheduler
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class PresenterFactory @Inject constructor(private val router: Router,
                                           private val repo: IEarthGalleryRepo,
                                           private val uiScheduler: Scheduler) {
    fun createEarthPhotoPresenter (earthPhotoServerResponse: EarthPhotoServerResponse) = EarthPhotoPresenter(earthPhotoServerResponse, router, repo, uiScheduler)
}