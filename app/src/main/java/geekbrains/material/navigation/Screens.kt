package geekbrains.material.navigation

import geekbrains.material.ui.fragment.PictureOfTheDayFragment
import geekbrains.material.ui.fragment.WikiSearchFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class PictureOfTheDayScreen() : SupportAppScreen() {
        override fun getFragment() = PictureOfTheDayFragment.newInstance()
    }

    class WikiSearchScreen() : SupportAppScreen() {
        override fun getFragment() = WikiSearchFragment.newInstance()
    }
}