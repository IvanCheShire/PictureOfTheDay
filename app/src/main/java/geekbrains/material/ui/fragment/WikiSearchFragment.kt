package geekbrains.material.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import geekbrains.material.R
import geekbrains.material.mvp.presenter.WikiSearchPresenter
import geekbrains.material.mvp.view.WikiSearchView
import geekbrains.material.ui.App
import geekbrains.material.ui.BackButtonListener
import kotlinx.android.synthetic.main.fragment_wiki_search.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject


class WikiSearchFragment: MvpAppCompatFragment(), WikiSearchView, BackButtonListener {

    init {
        App.instance.appComponent.inject(this)
    }
    companion object {
        fun newInstance() = WikiSearchFragment()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: WikiSearchPresenter

    @ProvidePresenter
    fun provide() = presenter

    lateinit var webView: WebView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val rootView = View.inflate(context, R.layout.fragment_wiki_search, null)
        webView = rootView.findViewById<WebView>(R.id.wiki_web_view)
        return rootView
    }


    override fun init() {
        input_layout.setEndIconOnClickListener {
            presenter.searchInWiki(input_edit_text.text.toString())
        }
    }

    override fun showWikiPage(url: String) {
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        webView.clearCache(true)
        webView.clearHistory()
        webView.settings.setJavaScriptEnabled(true)
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.visibility = View.VISIBLE
        webView.loadUrl(url)
    }

    override fun backPressed() = presenter.backClick()
}