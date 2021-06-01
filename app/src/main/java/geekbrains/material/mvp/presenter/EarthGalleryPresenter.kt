package geekbrains.material.mvp.presenter

import geekbrains.material.mvp.model.entity.EarthPhotoData
import geekbrains.material.mvp.model.entity.EarthPhotoServerResponse
import geekbrains.material.mvp.model.entity.PictureOfTheDayData
import geekbrains.material.mvp.model.repo.IEarthGalleryRepo
import geekbrains.material.mvp.view.EarthGalleryView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class EarthGalleryPresenter @Inject constructor(val router: Router, val repo: IEarthGalleryRepo, val uiScheduler: Scheduler): MvpPresenter<EarthGalleryView>() {

    class ViewPagerPresenter{

        var earthPhotos = mutableListOf<EarthPhotoServerResponse>()

        fun getEarthPhotoServerResponseAt(position: Int): EarthPhotoServerResponse {
            return earthPhotos[position]
        }

        fun getCount(): Int {
            return earthPhotos.size
        }

        fun getPageTitle(position: Int): CharSequence? {
            var pageTitle: CharSequence? = ""
            for (i in 0..earthPhotos.size) {
                if (i == position) pageTitle = earthPhotos[i].date
            }
            return pageTitle
        }
    }

    val viewPagerPresenter = ViewPagerPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        repo.getLastEarthPictures()
                .retry(3)
                .observeOn(uiScheduler)
                .subscribe(
                        { result ->
                            when(result){
                                is EarthPhotoData.Success -> {
                                    viewPagerPresenter.earthPhotos = result.serverResponseData as MutableList<EarthPhotoServerResponse>
                                    viewState.init()
                                }
                                is EarthPhotoData.Error -> viewState.showError(result.error.message)
                                is EarthPhotoData.Loading -> {  }
                            }
                        },
                        { println("onError: ${it.message}") })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}