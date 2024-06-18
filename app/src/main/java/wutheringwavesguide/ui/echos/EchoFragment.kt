package wutheringwavesguide.ui.echos

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


class EchoFragment : Fragment() {

    private lateinit var binding: FragmentEchoBinding
    private val wutheringGuidesService by viewModel<EchoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEchoBinding.inflate(inflater,container,false)
        val response = wutheringGuidesService.echoesLiveData
        return binding.root
    }

}