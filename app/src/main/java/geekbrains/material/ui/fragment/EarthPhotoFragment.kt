package geekbrains.material.ui.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import geekbrains.material.R
import geekbrains.material.mvp.model.entity.EarthPhotoServerResponse
import geekbrains.material.mvp.presenter.EarthPhotoPresenter
import geekbrains.material.mvp.presenter.PresenterFactory
import geekbrains.material.mvp.view.EarthPhotoView
import geekbrains.material.ui.App
import geekbrains.material.ui.BackButtonListener
import geekbrains.material.ui.utils.toast
import kotlinx.android.synthetic.main.fragment_earth_photo.*
import kotlinx.android.synthetic.main.fragment_picture_of_the_day.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.io.InputStream
import javax.inject.Inject


class EarthPhotoFragment  : MvpAppCompatFragment(), EarthPhotoView, BackButtonListener {

    @Inject
    lateinit var presenterFactory: PresenterFactory

    companion object {
        fun newInstance(earthPhotoServerResponse: EarthPhotoServerResponse) = EarthPhotoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EARTH_PHOTO, earthPhotoServerResponse)
            }
        }
        const val EARTH_PHOTO = "earthPhoto"
    }



    val presenter by moxyPresenter {
        presenterFactory.createEarthPhotoPresenter(this.arguments?.getParcelable<EarthPhotoServerResponse>(EARTH_PHOTO) as EarthPhotoServerResponse
        )
    }

    init {
        App.instance.appComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = View.inflate(context, R.layout.fragment_earth_photo, null)

    override fun init() {
        println("INIT FRAGMENT: ${this.toString()}.")
        presenter.loadData()
    }

    override fun showEarthImage(inputStream: InputStream?) {
        val bmp = BitmapFactory.decodeStream(inputStream)
        println("SHOWING EARTH")
        earth_image_view.load(bmp) {
            lifecycle(this@EarthPhotoFragment)
            error(R.drawable.ic_load_error_vector)
            placeholder(R.drawable.ic_no_photo_vector)
        }
    }

    override fun showError(message: String?) {
        toast(message)
    }


    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        println("SHOW LOADING ${(this.arguments?.getParcelable<EarthPhotoServerResponse>(EARTH_PHOTO) as EarthPhotoServerResponse).date}")
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        println("HIDE LOADING ${(this.arguments?.getParcelable<EarthPhotoServerResponse>(EARTH_PHOTO) as EarthPhotoServerResponse).date}")
    }

    override fun backPressed(): Boolean = presenter.backClick()

}