package geekbrains.material.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import geekbrains.material.R
import geekbrains.material.mvp.presenter.EarthGalleryPresenter
import geekbrains.material.mvp.view.EarthGalleryView
import geekbrains.material.ui.App
import geekbrains.material.ui.BackButtonListener
import geekbrains.material.ui.adapter.ViewPagerAdapter
import geekbrains.material.ui.utils.toast
import kotlinx.android.synthetic.main.fragment_earth_gallery.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class EarthGalleryFragment: MvpAppCompatFragment(), EarthGalleryView, BackButtonListener {

    companion object {
        fun newInstance() = EarthGalleryFragment()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: EarthGalleryPresenter

    @ProvidePresenter
    fun provide() = presenter

    init {
        App.instance.appComponent.inject(this)
        tab_layout.setupWithViewPager(view_pager)
    }

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View.inflate(context, R.layout.fragment_earth_gallery, null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun init() {
        view_pager.adapter = ViewPagerAdapter(presenter.viewPagerPresenter, childFragmentManager)
    }

    override fun showError(message: String?) {
        toast(message)
    }

    override fun backPressed(): Boolean = presenter.backClick()

}