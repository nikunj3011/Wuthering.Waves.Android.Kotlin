package wutheringwavesguide.ui.homedata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import wutheringwavesguide.R
import wutheringwavesguide.databinding.FragmentEchoBinding
import wutheringwavesguide.databinding.FragmentHomeDataBinding
import wutheringwavesguide.ui.echos.EchoViewModel
import wutheringwavesguide.util.autoCleared

class HomeDataFragment : Fragment() {

    var binding by autoCleared<FragmentHomeDataBinding>()
    private val viewModel by viewModel<HomeDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = FragmentHomeDataBinding.inflate(
            inflater,
            container,
            false
        )
        binding = dataBinding

        return binding.root
    }
}