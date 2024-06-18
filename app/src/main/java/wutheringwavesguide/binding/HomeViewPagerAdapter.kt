package wutheringwavesguide.binding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import wutheringwavesguide.CharacterFragment
import wutheringwavesguide.HomeFragment
import wutheringwavesguide.ui.echos.EchoFragment

class HomeViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CharacterFragment()
            1 -> EchoFragment()
            2 -> CharacterFragment()
            else -> CharacterFragment()
        }
    }

}