package geekbrains.material.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import geekbrains.material.mvp.presenter.NoteDialogPresenter
import geekbrains.material.mvp.view.NoteDialogView
import geekbrains.material.ui.App
import kotlinx.android.synthetic.main.dialog_fragment_note.*
import moxy.MvpAppCompatDialogFragment
import moxy.ktx.moxyPresenter


class NoteDialogFragment: MvpAppCompatDialogFragment(), NoteDialogView {

    companion object {
        fun newInstance(notePosition: Int, title: String, body: String?) = NoteDialogFragment().apply {
            arguments = Bundle().apply {
                putInt(NOTE_POSITION, notePosition)
                putString(TITLE, title)
                putString(BODY, body)
            }
        }
        const val NOTE_POSITION = "notePosition"
        private const val TITLE = "title"
        private const val BODY = "body"
    }

    val presenter by moxyPresenter { NoteDialogPresenter() }

    init {
        App.instance.appComponent.inject(this)
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        dialog?.setTitle("Задача")
        val v = View.inflate(context, com.lenatopoleva.pictureoftheday.R.layout.dialog_fragment_note, null)
        return v
    }

    override fun init() {
        btnYes.setOnClickListener{presenter.btnYesClicked()}
        btnCancel.setOnClickListener{presenter.btnCancelClicked()}
        note_title_edit_text.setText(arguments?.getString(TITLE))
        note_body_edit_text.setText(arguments?.getString(BODY))
    }

    override fun closeDialog() {
        this.dismiss()
    }

    override fun sendData() {
        (targetFragment as? DataTransmitter).let {
            arguments?.getInt(NOTE_POSITION)?.let { position ->
                it?.getData(
                        position,
                        note_title_edit_text.text.toString(),
                        note_body_edit_text.text.toString()
                )
            }
        }
    }
}