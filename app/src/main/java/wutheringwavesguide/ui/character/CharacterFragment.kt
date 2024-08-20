package wutheringwavesguide.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import wutheringwavesguide.databinding.FragmentCharacterBinding
import wutheringwavesguide.models.api.character.CharacterResponseItem
import wutheringwavesguide.ui.characterdetail.CharacterDetailBottomSheetFragment
import wutheringwavesguide.ui.common.CharacterListAdapter
import wutheringwavesguide.util.autoCleared
import java.util.Locale


class CharacterFragment : Fragment() {

    private lateinit var binding: FragmentCharacterBinding
    private val viewModel by viewModel<CharacterViewModel>()
    private var characterAdapter by autoCleared<CharacterListAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentCharacterBinding.inflate(
            inflater,
            container,
            false
        )
        binding = dataBinding
        binding.shimmerFrameLayoutCharacter.startShimmer()
        binding.characterRecyclerView.setVisibility(View.GONE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var response = viewModel.charactersLiveData
        binding.characterRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.searchCharacterView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText, false)
                return true
            }
        })
        binding.imageViewaero.setOnClickListener(View.OnClickListener {filterList("aero", true)})
        binding.imageViewhavoc.setOnClickListener(View.OnClickListener {filterList("havoc", true)})
        binding.imageViewfusion.setOnClickListener(View.OnClickListener {filterList("fusion", true)})
        binding.imageViewglacio.setOnClickListener(View.OnClickListener {filterList("glacio", true)})
        binding.imageViewspectro.setOnClickListener(View.OnClickListener {filterList("spectro", true)})
        binding.imageViewelectro.setOnClickListener(View.OnClickListener {filterList("electro", true)})
        binding.imageViewfilteroff.setOnClickListener(View.OnClickListener {filterList("", false)
            binding.searchCharacterView.setQuery("", true)})

        viewModel.viewModelScope.launch {
            initRecyclerView()
            delay(500)
            binding.shimmerFrameLayoutCharacter.setVisibility(View.GONE)
            binding.characterRecyclerView.setVisibility(View.VISIBLE)
        }
    }

    private fun filterList(query: String?, isElement: Boolean) {
        if (query != null) {
            viewModel.charactersLiveData.observe(viewLifecycleOwner) { result ->

                val filteredList = ArrayList<CharacterResponseItem>()
                if(isElement){
                    for (i in result) {
                        if (i.tag.lowercase(Locale.ROOT).contains(query)) {
                            filteredList.add(i)
                        }
                    }
                }
                else{
                    for (i in result) {
                        if (i.name.lowercase(Locale.ROOT).contains(query)) {
                            filteredList.add(i)
                        }
                    }
                }

                if (filteredList.isEmpty()) {
                    Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
                } else {
                    characterAdapter.setFilteredList(filteredList)
                }
            }
        }
    }

    private fun initRecyclerView() {
        viewModel.charactersLiveData.observe(viewLifecycleOwner) { result ->
            this.characterAdapter = CharacterListAdapter(requireContext(),
                result
            ) { selectedItem: CharacterResponseItem ->
                listItemClicked(selectedItem)
            }
            binding.characterRecyclerView.apply {
                adapter = characterAdapter
                itemAnimator?.changeDuration = 0
            }
        }
    }

    private fun listItemClicked(character: CharacterResponseItem){
        val bottomSheetFragment  = CharacterDetailBottomSheetFragment()

        val bundle = Bundle()
        bundle.putString("characterId", character.id)
        bottomSheetFragment.arguments = bundle
        bottomSheetFragment.setCancelable(true)
        bottomSheetFragment.show(requireActivity().supportFragmentManager, CharacterDetailBottomSheetFragment::class.java.name)

//        val textInputLayout = view1.findViewById<TextInputLayout>(wutheringwavesguide.R.id.textFieldLayout)
//        val editText = view1.findViewById<TextInputEditText>(wutheringwavesguide.R.id.editText)
//        val dismissBtn = view1.findViewById<MaterialButton>(wutheringwavesguide.R.id.dismiss)
//
//        dismissBtn.setOnClickListener {
//            if (Objects.requireNonNull(editText.text).toString().isEmpty()) {
//                textInputLayout.error = "Please type some text"
//            } else {
//                Toast.makeText(
//                    requireActivity(),
//                    editText.text.toString(),
//                    Toast.LENGTH_SHORT
//                ).show()
//
//                bottomSheetDialog.dismiss()
//            }
//        }
//
//        bottomSheetDialog.setOnDismissListener {
//            Toast.makeText(
//                requireActivity(),
//                "Bottom sheet dismissed",
//                Toast.LENGTH_SHORT
//            ).show()
//        }

//        Toast.makeText(
//            requireActivity(),
//            "Supplier is : ${character.name}",
//            Toast.LENGTH_LONG
//        ).show()
//        findNavController()
//            .navigate(
//                CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailedFragment(
//                    fruit.id
//                )
//            )
//        var dd = Dialog(requireActivity())
//        dd.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dd.setContentView(R.layout.fragment_character_detailed)
//
//        dd.setCancelable(false)
//        dd.show()
//
//        val bundle: Bundle = Bundle()
//        bundle.putString("characterId", character.id)
//        findNavController().navigate(R.id.characterDetailedFragment, bundle)
    }

}