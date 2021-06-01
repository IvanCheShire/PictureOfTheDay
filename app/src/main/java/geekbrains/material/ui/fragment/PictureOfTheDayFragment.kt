package geekbrains.material.ui.fragment

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isGone
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import geekbrains.material.ui.App
import geekbrains.material.ui.BackButtonListener
import geekbrains.material.ui.utils.toast
import geekbrains.material.R
import geekbrains.material.mvp.presenter.PictureOfTheDayPresenter
import geekbrains.material.mvp.view.PictureOfTheDayView
import kotlinx.android.synthetic.main.fragment_picture_of_the_day_start.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class PictureOfTheDayFragment: MvpAppCompatFragment(), PictureOfTheDayView, BackButtonListener {
    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: PictureOfTheDayPresenter

    @ProvidePresenter
    fun provide() = presenter

    init {
        App.instance.appComponent.inject(this)



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        val view = View.inflate(context, R.layout.fragment_picture_of_the_day_start, null)

        return view
    }



        override fun init() {
            pod_layout.setOnClickListener { presenter.onLayoutClicked()}
        }

    override fun showPicture(url: String) {
        image_view.load(url) {
            lifecycle(this@PictureOfTheDayFragment)
            error(R.drawable.ic_load_error_vector)
            placeholder(R.drawable.ic_no_photo_vector)
        }
    }

    override fun showError(message: String?) {
        toast(message)
    }

    override fun showDescription(description: String?) {
        pod_description.setText(description)
    }

    override fun showTitle(title: String?) {
        pod_description_header.setText(title)
    }

    override fun showVideo(url: String) {
        web_view.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        web_view.clearCache(true)
        web_view.clearHistory()
        web_view.settings.setJavaScriptEnabled(true)
        web_view.settings.javaScriptCanOpenWindowsAutomatically = true
        web_view.loadUrl(url)
    }

    override fun showWebView() {web_view.isGone = false}

    override fun hideImageView() {image_view.isGone = true}

    override fun showImageView() {image_view.isGone = false}

    override fun hideWebView() {web_view.isGone = true}

    override fun backPressed() = presenter.backClick()

    override fun showComponents() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(requireContext(), R.layout.fragment_picture_of_the_day_end)
        val transition = ChangeBounds()
        transition.interpolator = AccelerateInterpolator(1.0f)
        transition.duration = 400
        TransitionManager.beginDelayedTransition(pod_layout, transition)
        constraintSet.applyTo(pod_layout)
    }

    override fun hideComponents() {
        val constraintSet = ConstraintSet()
        constraintSet.clone(requireContext(), R.layout.fragment_picture_of_the_day_start)
        val transition = ChangeBounds()
        transition.interpolator = LinearInterpolator()
        transition.duration = 600
        TransitionManager.beginDelayedTransition(pod_layout, transition)
        constraintSet.applyTo(pod_layout)
    }

}