package geekbrains.material.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import geekbrains.material.mvp.presenter.EarthGalleryPresenter
import geekbrains.material.ui.fragment.EarthPhotoFragment

class ViewPagerAdapter(val presenter: EarthGalleryPresenter.ViewPagerPresenter, private val fragmentManager: FragmentManager) :
        FragmentStatePagerAdapter(fragmentManager) {

    lateinit var returnFragment: Fragment

    override fun getItem(position: Int): Fragment {
        for( i in 0..count){
            when(position) {
                i -> {
                    returnFragment = EarthPhotoFragment.newInstance(presenter.getEarthPhotoServerResponseAt(position))
                    println("CURRENT FRAGMENT: $position")
                    return returnFragment
                }
            }
        }
        return returnFragment
    }

    override fun getCount(): Int {
        return presenter.getCount()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return presenter.getPageTitle(position)
    }
}