package geekbrains.material.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface EarthGalleryView : MvpView {
    fun init()
    fun showError(message: String?)
}
