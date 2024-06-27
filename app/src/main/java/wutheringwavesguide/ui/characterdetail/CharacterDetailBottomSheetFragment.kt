package wutheringwavesguide.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import wutheringwavesguide.R
import wutheringwavesguide.databinding.FragmentCharacterDetailBottomSheetBinding
import wutheringwavesguide.databinding.FragmentCharacterDetailedBinding

class CharacterDetailBottomSheetFragment :  BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCharacterDetailBottomSheetBinding
    private val viewModel by viewModel<CharacterDetailsViewModel>()
    private var characterId : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = FragmentCharacterDetailBottomSheetBinding.inflate(
            inflater,
            container,
            false
        )

        characterId = arguments?.getString("characterId").toString()
        if(characterId == null)
        {
            characterId = "1"
        }
        binding = dataBinding
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.characterDetailsLiveData.observe(viewLifecycleOwner) { result ->
            var character = result.findLast { it.id == characterId }
            binding.characterNameBottomSheet.text = character?.name.toString()

            if(character?.img != null){
                Glide.with(view)
                    .load(character.img)
                    .into(binding.imageViewCharacterBottomSheet)
                if(character.bStyle == "linear-gradient(0deg, rgba(119,61,166,1) -79%, rgba(255,255,255,0) 100%)"){
                    binding.imageViewCharacterBottomSheet.background = ContextCompat.getDrawable(requireContext(), R.drawable.four_star_gradient)
                }
                else{
                    binding.imageViewCharacterBottomSheet.background = ContextCompat.getDrawable(requireContext(), R.drawable.five_star_gradient)
                }
            }
        }
    }
}