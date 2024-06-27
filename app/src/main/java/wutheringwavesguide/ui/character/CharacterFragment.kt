package wutheringwavesguide.ui.character

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import wutheringwavesguide.databinding.FragmentCharacterBinding
import wutheringwavesguide.databinding.FragmentCharacterDetailBottomSheetBinding
import wutheringwavesguide.models.api.character.CharacterResponseItem
import wutheringwavesguide.ui.characterdetail.CharacterDetailBottomSheetFragment
import wutheringwavesguide.ui.common.CharacterListAdapter
import wutheringwavesguide.util.autoCleared
import java.util.Objects


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
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var response = viewModel.charactersLiveData
        binding.characterRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        initRecyclerView()
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