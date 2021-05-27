package geekbrains.material.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import geekbrains.material.R
import geekbrains.material.mvp.presenter.EarthPresenter
import geekbrains.material.mvp.view.EarthView
import geekbrains.material.ui.App
import geekbrains.material.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class EarthFragment: MvpAppCompatFragment(), EarthView, BackButtonListener {

    companion object {
        fun newInstance() = EarthFragment()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: EarthPresenter

    @ProvidePresenter
    fun provide() = presenter

    init {
        App.instance.appComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_earth, null)

    override fun init() {}

    override fun backPressed(): Boolean = presenter.backClick()

}