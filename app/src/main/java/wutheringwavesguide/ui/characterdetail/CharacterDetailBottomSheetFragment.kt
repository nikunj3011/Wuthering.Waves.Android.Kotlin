package wutheringwavesguide.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import wutheringwavesguide.R
import wutheringwavesguide.databinding.FragmentCharacterDetailBottomSheetBinding

class CharacterDetailBottomSheetFragment :  BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCharacterDetailBottomSheetBinding
    private val viewModel by viewModel<CharacterDetailsViewModel>()
    private lateinit var characterId : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        viewModel.id = characterId
        viewModel.characterDetailsLiveData.observe(viewLifecycleOwner) { result ->
            var character = result
            binding.txtCharacterNameBottomSheet.text = character?.name.toString()
            binding.textCharacterNameTransparentBottomSheet.text = character?.name.toString()
            binding.textDescriptionBottomSheet.text = character?.introduction?.raw?.content?.first()?.content?.first()?.value

            binding.textMainDPSCharacterBottomSheet.text = "Main DPS  " + character?.rankings?.mainDPS
            binding.textOverallCharacterBottomSheet.text = "Overall     " + character?.rankings?.overall
            binding.textSupportCharacterBottomSheet.text = "Support     " + character?.rankings?.support
            binding.textSubDPSCharacterBottomSheet.text = "Sub-DPS  " + character?.rankings?.subDPS


            binding.txtMainEchoBottomSheet.text = character?.buildInfoEcho?.echo_sets?.first()?.name
            binding.txtCharacterWeaponTypeBottomSheet.text = character?.weapon.toString()

            if(character?.tag != null){
                when (character.tag) {
                    "Aero" -> {
                        Glide.with(view)
                            .load(R.drawable.aero)
                            .into(binding.imageViewCharacterElementBottomSheet)
                    }
                    "Glacio" -> {
                        Glide.with(view)
                            .load(R.drawable.glacio)
                            .into(binding.imageViewCharacterElementBottomSheet)
                    }
                    "Electro" -> {
                        Glide.with(view)
                            .load(R.drawable.electro)
                            .into(binding.imageViewCharacterElementBottomSheet)
                    }
                    "Fusion" -> {
                        Glide.with(view)
                            .load(R.drawable.fusion)
                            .into(binding.imageViewCharacterElementBottomSheet)
                    }
                    "Havoc" -> {
                        Glide.with(view)
                            .load(R.drawable.havoc)
                            .into(binding.imageViewCharacterElementBottomSheet)
                    }
                    "Spectro" -> {
                        Glide.with(view)
                            .load(R.drawable.spectro)
                            .into(binding.imageViewCharacterElementBottomSheet)
                    }
                    else -> {
                        Glide.with(view)
                            .load(R.drawable.spectro)
                            .into(binding.imageViewCharacterElementBottomSheet)
                    }
                }
            }

            if(character?.weapon != null){
                when (character.weapon) {
                    "Broadblade" -> {
                        Glide.with(view)
                            .load(R.drawable.broadblade)
                            .into(binding.imageViewWeaponTypeBottomSheet)
                    }
                    "Gauntlets" -> {
                        Glide.with(view)
                            .load(R.drawable.gauntlets)
                            .into(binding.imageViewWeaponTypeBottomSheet)
                    }
                    "Pistols" -> {
                        Glide.with(view)
                            .load(R.drawable.pistol)
                            .into(binding.imageViewWeaponTypeBottomSheet)
                    }
                    "Rectifier" -> {
                        Glide.with(view)
                            .load(R.drawable.rectifier)
                            .into(binding.imageViewWeaponTypeBottomSheet)
                    }
                    "Sword" -> {
                        Glide.with(view)
                            .load(R.drawable.sword)
                            .into(binding.imageViewWeaponTypeBottomSheet)
                    }
                    else -> {
                        Glide.with(view)
                            .load(R.drawable.sword)
                            .into(binding.imageViewWeaponTypeBottomSheet)
                    }
                }
            }

            if(character?.region != null){
                when (character.region) {
                    "Huanglong" -> {
                        Glide.with(view)
                            .load(R.drawable.huanglong)
                            .into(binding.imageViewCharacterRegionBottomSheet)
                    }
                    "Unknown" -> {
                        Glide.with(view)
                            .load(R.drawable.unknown)
                            .into(binding.imageViewCharacterRegionBottomSheet)
                    }
                    "Black Shores" -> {
                        Glide.with(view)
                            .load(R.drawable.black_shores)
                            .into(binding.imageViewCharacterRegionBottomSheet)
                    }
                    "Other" -> {
                        Glide.with(view)
                            .load(R.drawable.other)
                            .into(binding.imageViewCharacterRegionBottomSheet)
                    }
                    else -> {
                        Glide.with(view)
                            .load(R.drawable.other)
                            .into(binding.imageViewCharacterRegionBottomSheet)
                    }
                }
            }
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