package geekbrains.material.mvp.presenter

import geekbrains.material.ui.App
import geekbrains.material.mvp.view.MainView
import geekbrains.material.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter: MvpPresenter<MainView>() {
    @Inject lateinit var app: App

    @Inject lateinit var router: Router

    val primaryScreen = Screens.PictureOfTheDayScreen()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(primaryScreen)
    }

    fun backClick() {
        router.exit()
    }

    fun wikiMenuItemClicked() {
        router.navigateTo(Screens.WikiSearchScreen())
    }

}