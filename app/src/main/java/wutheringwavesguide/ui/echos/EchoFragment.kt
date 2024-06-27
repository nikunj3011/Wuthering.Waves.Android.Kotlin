package wutheringwavesguide.ui.echos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import wutheringwavesguide.binding.HomeViewPagerAdapter
import wutheringwavesguide.databinding.FragmentBookmarksBinding
import wutheringwavesguide.databinding.FragmentEchoBinding
import wutheringwavesguide.databinding.FragmentHomeBinding
import wutheringwavesguide.models.api.echo.EchoesResponseItem
import wutheringwavesguide.ui.bookmarks.BookmarksFragmentDirections
import wutheringwavesguide.ui.common.EchoListAdapter
import wutheringwavesguide.ui.common.NewsListAdapter
import wutheringwavesguide.util.autoCleared


class EchoFragment : Fragment() {

    var binding by autoCleared<FragmentEchoBinding>()
    private val viewModel by viewModel<EchoViewModel>()
    private var echosAdapter by autoCleared<EchoListAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentEchoBinding.inflate(
            inflater,
            container,
            false
        )
        binding = dataBinding

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var response = viewModel.echoesLiveData
        binding.echoRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        initRecyclerView()
    }

    private fun initRecyclerView() {
        viewModel.echoesLiveData.observe(viewLifecycleOwner) { result ->
            this.echosAdapter = EchoListAdapter(
                result
            ) { selectedItem: EchoesResponseItem ->
                listItemClicked(selectedItem)
            }
            binding.echoRecyclerView.apply {
                adapter = echosAdapter
                itemAnimator?.changeDuration = 0
            }
        }
    }

    private fun listItemClicked(fruit: EchoesResponseItem){
        Toast.makeText(
            requireActivity(),
            "Supplier is : ${fruit.name}",
            Toast.LENGTH_LONG
        ).show()
    }
}