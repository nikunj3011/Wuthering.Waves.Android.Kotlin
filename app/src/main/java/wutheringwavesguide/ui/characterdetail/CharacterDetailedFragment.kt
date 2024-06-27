package wutheringwavesguide.ui.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import wutheringwavesguide.R
import wutheringwavesguide.databinding.FragmentCharacterDetailedBinding

class CharacterDetailedFragment : Fragment() {
    private lateinit var binding: FragmentCharacterDetailedBinding
    private val viewModel by viewModel<CharacterDetailsViewModel>()
    private var characterId : String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = FragmentCharacterDetailedBinding.inflate(
            inflater,
            container,
            false
        )
        binding = dataBinding
        characterId = arguments?.getString("characterId").toString()
        if(characterId == null)
        {
            characterId = "1"
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.characterDetailsLiveData.observe(viewLifecycleOwner) { result ->
            var character = result.findLast { it.id == characterId }
            binding.characterNameDetail.text = character?.name.toString()

            if(character?.img != null){
                Glide.with(view)
                    .load(character.img)
                    .into(binding.imageViewCharacterDetail)
                if(character.bStyle == "linear-gradient(0deg, rgba(119,61,166,1) -79%, rgba(255,255,255,0) 100%)"){
                    binding.imageViewCharacterDetail.background = ContextCompat.getDrawable(requireContext(), R.drawable.four_star_gradient)
                }
                else{
                    binding.imageViewCharacterDetail.background = ContextCompat.getDrawable(requireContext(), R.drawable.five_star_gradient)
                }
            }
        }
    }
}