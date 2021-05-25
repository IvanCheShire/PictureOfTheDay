package geekbrains.material.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainView: MvpView {
    fun setPODMenuItemChecked()
    fun setWikiMenuItemChecked()
    fun setSettingsMenuItemChecked()
    fun setEarthMenuItemChecked()
}