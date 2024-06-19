package wutheringwavesguide.binding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import wutheringwavesguide.ui.character.CharacterFragment
import wutheringwavesguide.ui.echos.EchoFragment
import wutheringwavesguide.ui.weapons.WeaponFragment

class HomeViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CharacterFragment()
            1 -> CharacterFragment()
            2 -> EchoFragment()
            3 -> WeaponFragment()
            else -> CharacterFragment()
        }
    }

}