package wutheringwavesguide.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import wutheringwavesguide.R
import wutheringwavesguide.databinding.FragmentCharacterDetailBottomSheetBinding


class CharacterDetailBottomSheetFragment :  BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCharacterDetailBottomSheetBinding
    private val viewModel by viewModel<CharacterDetailsViewModel>()
    private lateinit var characterId : String
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
//        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, com.google.android.material.R.style.Animation_Material3_BottomSheetDialog);
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = FragmentCharacterDetailBottomSheetBinding.inflate(
            inflater,
            container,
            false
        )

//        val window: Window = context.win
//        window.setBackgroundDrawableResource(android.R.color.transparent)
//        val lp = window.attributes
//        lp.alpha = 1.0f
//        lp.dimAmount = 0.0f
//        window.attributes = lp
        characterId = arguments?.getString("characterId").toString()
        if(characterId == null)
        {
            characterId = "1"
        }
        binding = dataBinding
        view?.parent
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val bottomSheet : ConstraintLayout = dialog?.findViewById(R.id.characterListItemBottomSheet)!!
//
//        // Height of the view
//        bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//
//        // Behavior of the bottom sheet
//        val behavior = BottomSheetBehavior.from(bottomSheet)
//        behavior.apply {
//            peekHeight = resources.displayMetrics.heightPixels // Pop-up height
//            state = BottomSheetBehavior.STATE_EXPANDED // Expanded state
//
//            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//                override fun onStateChanged(bottomSheet: View, newState: Int) {
//                }
//
//                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
//            })
//        }

        viewModel.id = characterId
        viewModel.characterDetailsLiveData.observe(viewLifecycleOwner) { result ->
            var character = result
            binding.txtCharacterNameBottomSheet.text = character?.name.toString()
            binding.textCharacterNameTransparentBottomSheet.text = character?.name.toString()
            binding.textDescriptionBottomSheet.text = character?.introduction?.raw?.content?.first()?.content?.first()?.value

            binding.textMainDPSCharacterBottomSheet.text = "Main DPS  S"// + character?.rankings?.mainDPS
            binding.textOverallCharacterBottomSheet.text = "Overall     S"// + character?.rankings?.overall
            binding.textSupportCharacterBottomSheet.text = "Support     S" //+ character?.rankings?.support
            binding.textSubDPSCharacterBottomSheet.text = "Sub-DPS  S" //+ character?.rankings?.subDPS


            binding.txtBestSetBottomSheet.text = character?.buildInfoEcho?.echo_sets?.first()?.name
            binding.txtMainEchoBottomSheet.text = character?.buildInfoEcho?.echo_sets?.first()?.main_echo
            binding.textViewBestWeapon.text = character?.buildInfoWeapon?.first()?.weapon
            binding.txtCharacterWeaponTypeBottomSheet.text = character?.weapon.toString()
            binding.textCharDesSmall.text = character?.region + "_@2024"
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
                    Glide.with(view)
                        .load(R.drawable.four_star_flat)
                        .into(binding.imageViewStarGradient)
//                    binding.imageViewCharacterBottomSheet.background = ContextCompat.getDrawable(requireContext(), R.drawable.four_star_gradient)
                }
                else{
                    Glide.with(view)
                        .load(R.drawable.five_star_flat)
                        .into(binding.imageViewStarGradient)
//                    binding.imageViewCharacterBottomSheet.background = ContextCompat.getDrawable(requireContext(), R.drawable.five_star_gradient)
                }
            }


        }
    }
}