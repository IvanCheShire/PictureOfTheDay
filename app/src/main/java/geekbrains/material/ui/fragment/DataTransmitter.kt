package geekbrains.material.ui.fragment

interface DataTransmitter {
    fun getData(notePosition: Int, title: String, body: String)
}