package wutheringwavesguide

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
import wutheringwavesguide.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val wutheringGuidesService by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().startService(Intent(context, wutheringGuidesService::class.java))
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val homeViewPager: ViewPager2 = binding.viewPagerHome
        val homeTabLayout: TabLayout = binding.homeTabLayout
        homeViewPager.adapter = HomeViewPagerAdapter(requireActivity())
        TabLayoutMediator(homeTabLayout, homeViewPager) { tab, position ->
            when(position){
                0 -> tab.text = "Home"
                1 -> tab.text = "Characters"
                2 -> tab.text = "Echos"
                3 -> tab.text = "Weapons"
            }
        }.attach()
        val response = wutheringGuidesService.echoesLiveData
        return binding.root
        // Inflate the layout for this fragment
//        binding.btnGoToCharacters.setOnClickListener {
//            val bundle = bundleOf("textviewData" to binding.textView3.text.toString())
//            it.findNavController().navigate(R.id.action_homeFragment_to_characterFragment, bundle)
//        }
//        binding.btnGoToCharacters.isVisible = false
//        binding.textView3.isVisible = false
    }

}