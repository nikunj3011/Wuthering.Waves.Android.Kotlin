package wutheringwavesguide.ui.weapons

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import wutheringwavesguide.binding.HomeViewPagerAdapter
import wutheringwavesguide.databinding.FragmentEchoBinding
import wutheringwavesguide.databinding.FragmentHomeBinding
import wutheringwavesguide.databinding.FragmentWeaponBinding
import wutheringwavesguide.models.api.echo.EchoesResponseItem
import wutheringwavesguide.models.api.weapon.WeaponResponseItem
import wutheringwavesguide.ui.common.EchoListAdapter
import wutheringwavesguide.ui.common.WeaponListAdapter
import wutheringwavesguide.util.autoCleared


class WeaponFragment : Fragment() {

    private lateinit var binding: FragmentWeaponBinding
    private val viewModel by viewModel<WeaponViewModel>()
    private var weaponsAdapter by autoCleared<WeaponListAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = FragmentWeaponBinding.inflate(
            inflater,
            container,
            false
        )
        binding = dataBinding

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var response = viewModel.weaponsLiveData
        binding.weaponRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        initRecyclerView()
    }

    private fun initRecyclerView() {
        viewModel.weaponsLiveData.observe(viewLifecycleOwner) { result ->
            this.weaponsAdapter = WeaponListAdapter(
                result
            ) { selectedItem: WeaponResponseItem ->
                listItemClicked(selectedItem)
            }
            binding.weaponRecyclerView.apply {
                adapter = weaponsAdapter
                itemAnimator?.changeDuration = 0
            }
        }
    }

    private fun listItemClicked(fruit: WeaponResponseItem){
        Toast.makeText(
            requireActivity(),
            "Supplier is : ${fruit.name}",
            Toast.LENGTH_LONG
        ).show()
    }
}