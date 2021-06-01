package geekbrains.material.mvp.presenter

import geekbrains.material.mvp.view.EarthView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class EarthPresenter @Inject constructor(val router: Router): MvpPresenter<EarthView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}