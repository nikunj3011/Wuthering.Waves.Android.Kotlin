package wutheringwavesguide.ui.weapons

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import wutheringwavesguide.binding.HomeViewPagerAdapter
import wutheringwavesguide.databinding.FragmentEchoBinding
import wutheringwavesguide.databinding.FragmentHomeBinding
import wutheringwavesguide.databinding.FragmentWeaponBinding


class WeaponFragment : Fragment() {

    private lateinit var binding: FragmentWeaponBinding
    private val wutheringGuidesService by viewModel<WeaponViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeaponBinding.inflate(inflater,container,false)
        val response = wutheringGuidesService.weaponsLiveData
        return binding.root
    }

}