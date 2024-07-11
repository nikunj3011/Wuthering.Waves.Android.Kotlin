package wutheringwavesguide.ui.echos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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
import wutheringwavesguide.models.api.character.CharacterResponseItem
import wutheringwavesguide.models.api.echo.EchoesResponseItem
import wutheringwavesguide.ui.bookmarks.BookmarksFragmentDirections
import wutheringwavesguide.ui.common.EchoListAdapter
import wutheringwavesguide.ui.common.NewsListAdapter
import wutheringwavesguide.util.autoCleared
import java.util.Locale


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

        binding.searchEchoView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
        initRecyclerView()
    }

    private fun filterList(query: String?) {
        if (query != null) {
            viewModel.echoesLiveData.observe(viewLifecycleOwner) { result ->

                val filteredList = ArrayList<EchoesResponseItem>()
                for (i in result) {
                    if (i.name.lowercase(Locale.ROOT).contains(query)) {
                        filteredList.add(i)
                    }
                }

                if (filteredList.isEmpty()) {
                    Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
                } else {
                    echosAdapter.setFilteredList(filteredList)
                }
            }
        }
    }

    private fun initRecyclerView() {
        viewModel.echoesLiveData.observe(viewLifecycleOwner) { result ->
            this.echosAdapter = EchoListAdapter(
                requireContext(),
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