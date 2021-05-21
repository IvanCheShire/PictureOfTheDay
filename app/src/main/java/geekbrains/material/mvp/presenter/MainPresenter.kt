package geekbrains.material.mvp.presenter

import geekbrains.material.ui.App
import geekbrains.material.mvp.view.MainView
import geekbrains.material.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter @Inject constructor (val app: App, val router: Router): MvpPresenter<MainView>() {


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

    fun settingsMenuItemClicked() {
        router.navigateTo(Screens.SettingsScreen())
    }

}