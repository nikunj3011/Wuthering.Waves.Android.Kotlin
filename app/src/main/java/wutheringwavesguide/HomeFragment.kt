package wutheringwavesguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import wutheringwavesguide.binding.HomeViewPagerAdapter
import wutheringwavesguide.HomeActivity
import wutheringwavesguide.databinding.ActivityHomeBinding
import wutheringwavesguide.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
//        binding.btnGoToCharacters.setOnClickListener {
//            val bundle = bundleOf("textviewData" to binding.textView3.text.toString())
//            it.findNavController().navigate(R.id.action_homeFragment_to_characterFragment, bundle)
//        }
//        binding.btnGoToCharacters.isVisible = false
//        binding.textView3.isVisible = false
        val homeViewPager: ViewPager2 = binding.viewPagerHome
        val homeTabLayout: TabLayout = binding.homeTabLayout
        homeViewPager.adapter = HomeViewPagerAdapter(requireActivity())
        TabLayoutMediator(homeTabLayout, homeViewPager) { tab, position ->
            when(position){
                0 -> tab.text = "Home"
                1 -> tab.text = "Character"
            }
        }.attach()
        return binding.root
    }

}