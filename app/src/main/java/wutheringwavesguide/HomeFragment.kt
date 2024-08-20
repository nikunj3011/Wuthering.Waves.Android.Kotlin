package wutheringwavesguide

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import wutheringwavesguide.binding.HomeViewPagerAdapter
import wutheringwavesguide.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val wutheringGuidesService by viewModel<HomeViewModel>()
    private lateinit var sf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var charactersData:String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sf = requireActivity().getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE)
        editor = sf.edit()
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
        val response = wutheringGuidesService.characterDetailsLiveData
        return binding.root
        // Inflate the layout for this fragment
//        binding.btnGoToCharacters.setOnClickListener {
//            val bundle = bundleOf("textviewData" to binding.textView3.text.toString())
//            it.findNavController().navigate(R.id.action_homeFragment_to_characterFragment, bundle)
//        }
//        binding.btnGoToCharacters.isVisible = false
//        binding.textView3.isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var c = 0
        wutheringGuidesService.characterDetailsLiveData.observe(viewLifecycleOwner) { result ->
            editor.apply{

                var gson = Gson()
                var characterJson = gson.toJson(result)
                putString("charactersDataWuthering", characterJson)
//                putString("charactersDataZZZModifiedDate", Calendar.getInstance().time.toString())
                commit()
            }
        }
    }

}