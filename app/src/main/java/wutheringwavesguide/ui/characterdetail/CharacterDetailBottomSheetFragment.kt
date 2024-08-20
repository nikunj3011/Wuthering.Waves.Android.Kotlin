package wutheringwavesguide.ui.characterdetail

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel
import wutheringwavesguide.R
import wutheringwavesguide.animations.Bounce
import wutheringwavesguide.animations.Fade
import wutheringwavesguide.animations.animationXBounce
import wutheringwavesguide.animations.animationXFade
import wutheringwavesguide.databinding.FragmentCharacterDetailBottomSheetBinding
import wutheringwavesguide.models.api.characterdetail.CharacterDetailResponse
import wutheringwavesguide.models.api.characterdetail.ListCharacterDetailResponse
import wutheringwavesguide.models.api.characterdetails.CharacterDetailsResponseItem
import wutheringwavesguide.models.api.characterdetails.CharactersDetailsResponse


class CharacterDetailBottomSheetFragment :  BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCharacterDetailBottomSheetBinding
    private val viewModel by viewModel<CharacterDetailsViewModel>()
    private lateinit var characterId : String
    private lateinit var sf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var charactersModel : ListCharacterDetailResponse
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

//        binding.shimmerFrameLayoutCharacterDetailBottomSheet.startShimmer()
//        binding.characterListItemBottomSheetSub.setVisibility(View.GONE)
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

        sf = requireActivity().getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE)
        editor = sf.edit()
        var charactersData = sf.getString("charactersDataWuthering", "")

        if (!charactersData.isNullOrEmpty()) {
            var gson = Gson()
            charactersModel = gson.fromJson(charactersData, ListCharacterDetailResponse::class.java)
            var character = charactersModel.findLast { c -> c.id == characterId }
            binding.txtCharacterNameBottomSheet.text = character?.name.toString()

            binding.textCharacterNameTransparentBottomSheet.text = character?.name.toString()
            binding.textDescriptionBottomSheet.text =
                character?.introduction?.raw?.content?.first()?.content?.first()?.value

            binding.textMainCategoryCharacterBottomSheet.text =
                "Main Role: ${character?.tierCategory}"

            var characterSubCategories = ""
            if (!character?.tierTags.isNullOrEmpty()) {
                for (name in character?.tierTags!!) {
                    characterSubCategories = "$characterSubCategories $name "
                }
            }
            binding.textSubCategoryCharacterBottomSheet.text =
                "Other Roles: $characterSubCategories"

            binding.textRatingsCharacterBottomSheet.text =
                "Tower of Adversity: " + character?.ratings?.tower?.toString()


            binding.txtBestSetBottomSheet.text =
                "5x: " + character?.buildInfoEcho?.echo_sets?.first()?.name
            binding.txtMainEchoBottomSheet.text =
                character?.buildInfoEcho?.echo_sets?.first()?.main_echo
            binding.textViewBestWeapon.text = character?.buildInfoWeapon?.first()?.weapon
            binding.txtCharacterWeaponTypeBottomSheet.text = character?.weapon.toString()
            binding.textCharDesSmall.text = character?.region + "_@2024"

            var echo4PCStat = ""
            if (!character?.buildInfoEcho?.echo_4_stat.isNullOrEmpty()) {
                for (name in character?.buildInfoEcho?.echo_4_stat!!) {
                    echo4PCStat = echo4PCStat + " " + name.stat + " "
                }
            }
            binding.textView4CEchoSet.text = echo4PCStat

            var echo3PCStat = ""
            if (!character?.buildInfoEcho?.echo_3_stat.isNullOrEmpty()) {
                for (name in character?.buildInfoEcho?.echo_3_stat!!) {
                    echo3PCStat = echo3PCStat + " " + name.stat + " "
                }
            }
            binding.textView3CEchoSet.text = echo3PCStat

            var echo1PCStat = ""
            if (!character?.buildInfoEcho?.echo_1_stat.isNullOrEmpty()) {
                for (name in character?.buildInfoEcho?.echo_1_stat!!) {
                    echo1PCStat = echo1PCStat + " " + name.stat + " "
                }
            }
            binding.textView1CEchoSet.text = echo1PCStat

            var teammates = ""
            if (!character?.synergies.isNullOrEmpty()) {
                for (name in character?.synergies!!) {
                    teammates = teammates + " " + name.char + " "
                }
            }

            binding.textViewCharacterBestTeammates.text = teammates

            binding.textViewCharacterSubStats.text = character?.buildInfoOther?.substats

            var characterAscension = ""
            if (!character?.ascensionMaterials?.common.isNullOrEmpty()) {
                for (name in character?.ascensionMaterials?.common!!) {
                    characterAscension =
                        "$characterAscension \n${name.number_char} x ${name.name} "
                }
            }
            if (character?.ascensionMaterials?.ascension != null) {
                characterAscension =
                    "$characterAscension \n${character?.ascensionMaterials?.ascension?.number} x ${character?.ascensionMaterials?.ascension?.name} "
            }
            if (character?.ascensionMaterials?.breakthrough != null) {
                characterAscension =
                    "$characterAscension \n${character?.ascensionMaterials?.breakthrough?.number} x ${character?.ascensionMaterials?.breakthrough?.name} "
            }
            if (character?.ascensionMaterials?.skill != null) {
                characterAscension =
                    "$characterAscension \n${character?.ascensionMaterials?.skill?.number} x ${character?.ascensionMaterials?.skill?.name} "
            }
            binding.textViewCharacterAscensionMaterials.text = characterAscension

            var characterSkillUpgrades = ""
            if (!character?.ascensionMaterials?.common.isNullOrEmpty()) {
                for (name in character?.ascensionMaterials?.common!!) {
                    characterSkillUpgrades =
                        "$characterSkillUpgrades \n${name.number_skill} x ${name.name} "
                }
            }
            if (!character?.ascensionMaterials?.skill_other.isNullOrEmpty()) {
                for (name in character?.ascensionMaterials?.skill_other!!) {
                    characterSkillUpgrades =
                        "$characterSkillUpgrades \n${name.number} x ${name.name} "
                }
            }

            binding.textViewCharacterSkillUpgradesMaterials.text = characterSkillUpgrades


            if (character?.img != null) {
                Glide.with(view)
                    .load(character.img)
                    .into(binding.imageViewCharacterBottomSheet)
                if (character.bStyle == "linear-gradient(0deg, rgba(119,61,166,1) -79%, rgba(255,255,255,0) 100%)") {
                    Glide.with(view)
                        .load(R.drawable.four_star_flat)
                        .into(binding.imageViewStarGradient)
                    //                    binding.imageViewCharacterBottomSheet.background = ContextCompat.getDrawable(requireContext(), R.drawable.four_star_gradient)
                } else {
                    Glide.with(view)
                        .load(R.drawable.five_star_flat)
                        .into(binding.imageViewStarGradient)
                    //                    binding.imageViewCharacterBottomSheet.background = ContextCompat.getDrawable(requireContext(), R.drawable.five_star_gradient)
                }
            }
            var backgroundColor = ContextCompat.getColor(requireContext(), R.color.purple_200)

            if (character?.tag != null) {
                when (character.tag) {
                    "Aero" -> {
                        Glide.with(view)
                            .load(R.drawable.aero)
                            .into(binding.imageViewCharacterElementBottomSheet)
                        backgroundColor =
                            ContextCompat.getColor(requireContext(), R.color.color_aero)
                    }

                    "Glacio" -> {
                        Glide.with(view)
                            .load(R.drawable.glacio)
                            .into(binding.imageViewCharacterElementBottomSheet)
                        backgroundColor =
                            ContextCompat.getColor(requireContext(), R.color.color_glacio)

                    }

                    "Electro" -> {
                        Glide.with(view)
                            .load(R.drawable.electro)
                            .into(binding.imageViewCharacterElementBottomSheet)
                        backgroundColor =
                            ContextCompat.getColor(requireContext(), R.color.color_electro)
                    }

                    "Fusion" -> {
                        Glide.with(view)
                            .load(R.drawable.fusion)
                            .into(binding.imageViewCharacterElementBottomSheet)
                        backgroundColor =
                            ContextCompat.getColor(requireContext(), R.color.color_fusion)
                    }

                    "Havoc" -> {
                        Glide.with(view)
                            .load(R.drawable.havoc)
                            .into(binding.imageViewCharacterElementBottomSheet)
                        backgroundColor =
                            ContextCompat.getColor(requireContext(), R.color.color_havoc)
                    }

                    "Spectro" -> {
                        Glide.with(view)
                            .load(R.drawable.spectro)
                            .into(binding.imageViewCharacterElementBottomSheet)
                        backgroundColor =
                            ContextCompat.getColor(requireContext(), R.color.color_spectro)
                    }

                    else -> {
                        Glide.with(view)
                            .load(R.drawable.spectro)
                            .into(binding.imageViewCharacterElementBottomSheet)
                        backgroundColor =
                            ContextCompat.getColor(requireContext(), R.color.color_glacio)
                    }
                }
            }

            binding.viewCharacterNameBottomSheet.setBackgroundColor(backgroundColor)
            binding.viewCharacterLineWeaponLineBottomSheet.setBackgroundColor(backgroundColor)
            binding.viewCharacterLineMainEchoLineBottomSheet.setBackgroundColor(backgroundColor)
            binding.viewCharacterLineEchoLineBottomSheet.setBackgroundColor(backgroundColor)
            binding.viewCharacterLineEchoSetLineBottomSheet.setBackgroundColor(backgroundColor)
            binding.viewCharacterLineSubStatsLineBottomSheet.setBackgroundColor(backgroundColor)
            binding.viewCharacterLineTeamLineBottomSheet.setBackgroundColor(backgroundColor)
            binding.viewCharacterLineRatingLineBottomSheet.setBackgroundColor(backgroundColor)
            binding.viewCharacterLineUpgradeMatLineBottomSheet.setBackgroundColor(
                backgroundColor
            )
            binding.viewCharacterLineCharAscBottomSheet.setBackgroundColor(backgroundColor)
            binding.viewCharacterLineCharSkillUpBottomSheet.setBackgroundColor(backgroundColor)

            if (character?.weapon != null) {
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

            if (character?.region != null) {
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
            binding.txtCharacterNameBottomSheet.animationXFade(Fade.FADE_IN_RIGHT)
            binding.textCharacterNameTransparentBottomSheet.animationXFade(Fade.FADE_IN_RIGHT)
            binding.textCharDesSmall.animationXFade(Fade.FADE_IN_RIGHT)
            binding.imageViewCharacterRegionBottomSheet.animationXFade(Fade.FADE_IN_UP)
            binding.imageViewCharacterBottomSheet.animationXFade(Fade.FADE_IN_UP)
//            binding.txtCharacterNameBottomSheet.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in))

//            binding.shimmerFrameLayoutCharacterDetailBottomSheet.setVisibility(View.GONE)
//            binding.characterListItemBottomSheetSub.setVisibility(View.VISIBLE)
        }
        else
        {
            viewModel.id = characterId
            viewModel.characterDetailsLiveData.observe(viewLifecycleOwner) { result ->
                var character = result
                binding.txtCharacterNameBottomSheet.text = character?.name.toString()

                binding.textCharacterNameTransparentBottomSheet.text = character?.name.toString()
                binding.textDescriptionBottomSheet.text =
                    character?.introduction?.raw?.content?.first()?.content?.first()?.value

                binding.textMainCategoryCharacterBottomSheet.text =
                    "Main Role: ${character?.tierCategory}"

                var characterSubCategories = ""
                if (!character?.tierTags.isNullOrEmpty()) {
                    for (name in character?.tierTags!!) {
                        characterSubCategories = "$characterSubCategories $name "
                    }
                }
                binding.textSubCategoryCharacterBottomSheet.text =
                    "Other Roles: $characterSubCategories"

                binding.textRatingsCharacterBottomSheet.text =
                    "Tower of Adversity: " + character?.ratings?.tower?.toString()


                binding.txtBestSetBottomSheet.text =
                    "5x: " + character?.buildInfoEcho?.echo_sets?.first()?.name
                binding.txtMainEchoBottomSheet.text =
                    character?.buildInfoEcho?.echo_sets?.first()?.main_echo
                binding.textViewBestWeapon.text = character?.buildInfoWeapon?.first()?.weapon
                binding.txtCharacterWeaponTypeBottomSheet.text = character?.weapon.toString()
                binding.textCharDesSmall.text = character?.region + "_@2024"

                var echo4PCStat = ""
                if (!character?.buildInfoEcho?.echo_4_stat.isNullOrEmpty()) {
                    for (name in character?.buildInfoEcho?.echo_4_stat!!) {
                        echo4PCStat = echo4PCStat + " " + name.stat + " "
                    }
                }
                binding.textView4CEchoSet.text = echo4PCStat

                var echo3PCStat = ""
                if (!character?.buildInfoEcho?.echo_3_stat.isNullOrEmpty()) {
                    for (name in character?.buildInfoEcho?.echo_3_stat!!) {
                        echo3PCStat = echo3PCStat + " " + name.stat + " "
                    }
                }
                binding.textView3CEchoSet.text = echo3PCStat

                var echo1PCStat = ""
                if (!character?.buildInfoEcho?.echo_1_stat.isNullOrEmpty()) {
                    for (name in character?.buildInfoEcho?.echo_1_stat!!) {
                        echo1PCStat = echo1PCStat + " " + name.stat + " "
                    }
                }
                binding.textView1CEchoSet.text = echo1PCStat

                var teammates = ""
                if (!character?.synergies.isNullOrEmpty()) {
                    for (name in character?.synergies!!) {
                        teammates = teammates + " " + name.char + " "
                    }
                }

                binding.textViewCharacterBestTeammates.text = teammates

                binding.textViewCharacterSubStats.text = character?.buildInfoOther?.substats

                var characterAscension = ""
                if (!character?.ascensionMaterials?.common.isNullOrEmpty()) {
                    for (name in character?.ascensionMaterials?.common!!) {
                        characterAscension =
                            "$characterAscension \n${name.number_char} x ${name.name} "
                    }
                }
                if (character?.ascensionMaterials?.ascension != null) {
                    characterAscension =
                        "$characterAscension \n${character?.ascensionMaterials?.ascension?.number} x ${character?.ascensionMaterials?.ascension?.name} "
                }
                if (character?.ascensionMaterials?.breakthrough != null) {
                    characterAscension =
                        "$characterAscension \n${character?.ascensionMaterials?.breakthrough?.number} x ${character?.ascensionMaterials?.breakthrough?.name} "
                }
                if (character?.ascensionMaterials?.skill != null) {
                    characterAscension =
                        "$characterAscension \n${character?.ascensionMaterials?.skill?.number} x ${character?.ascensionMaterials?.skill?.name} "
                }
                binding.textViewCharacterAscensionMaterials.text = characterAscension

                var characterSkillUpgrades = ""
                if (!character?.ascensionMaterials?.common.isNullOrEmpty()) {
                    for (name in character?.ascensionMaterials?.common!!) {
                        characterSkillUpgrades =
                            "$characterSkillUpgrades \n${name.number_skill} x ${name.name} "
                    }
                }
                if (!character?.ascensionMaterials?.skill_other.isNullOrEmpty()) {
                    for (name in character?.ascensionMaterials?.skill_other!!) {
                        characterSkillUpgrades =
                            "$characterSkillUpgrades \n${name.number} x ${name.name} "
                    }
                }

                binding.textViewCharacterSkillUpgradesMaterials.text = characterSkillUpgrades


                if (character?.img != null) {
                    Glide.with(view)
                        .load(character.img)
                        .into(binding.imageViewCharacterBottomSheet)
                    if (character.bStyle == "linear-gradient(0deg, rgba(119,61,166,1) -79%, rgba(255,255,255,0) 100%)") {
                        Glide.with(view)
                            .load(R.drawable.four_star_flat)
                            .into(binding.imageViewStarGradient)
                        //                    binding.imageViewCharacterBottomSheet.background = ContextCompat.getDrawable(requireContext(), R.drawable.four_star_gradient)
                    } else {
                        Glide.with(view)
                            .load(R.drawable.five_star_flat)
                            .into(binding.imageViewStarGradient)
                        //                    binding.imageViewCharacterBottomSheet.background = ContextCompat.getDrawable(requireContext(), R.drawable.five_star_gradient)
                    }
                }
                var backgroundColor = ContextCompat.getColor(requireContext(), R.color.purple_200)

                if (character?.tag != null) {
                    when (character.tag) {
                        "Aero" -> {
                            Glide.with(view)
                                .load(R.drawable.aero)
                                .into(binding.imageViewCharacterElementBottomSheet)
                            backgroundColor =
                                ContextCompat.getColor(requireContext(), R.color.color_aero)
                        }

                        "Glacio" -> {
                            Glide.with(view)
                                .load(R.drawable.glacio)
                                .into(binding.imageViewCharacterElementBottomSheet)
                            backgroundColor =
                                ContextCompat.getColor(requireContext(), R.color.color_glacio)

                        }

                        "Electro" -> {
                            Glide.with(view)
                                .load(R.drawable.electro)
                                .into(binding.imageViewCharacterElementBottomSheet)
                            backgroundColor =
                                ContextCompat.getColor(requireContext(), R.color.color_electro)
                        }

                        "Fusion" -> {
                            Glide.with(view)
                                .load(R.drawable.fusion)
                                .into(binding.imageViewCharacterElementBottomSheet)
                            backgroundColor =
                                ContextCompat.getColor(requireContext(), R.color.color_fusion)
                        }

                        "Havoc" -> {
                            Glide.with(view)
                                .load(R.drawable.havoc)
                                .into(binding.imageViewCharacterElementBottomSheet)
                            backgroundColor =
                                ContextCompat.getColor(requireContext(), R.color.color_havoc)
                        }

                        "Spectro" -> {
                            Glide.with(view)
                                .load(R.drawable.spectro)
                                .into(binding.imageViewCharacterElementBottomSheet)
                            backgroundColor =
                                ContextCompat.getColor(requireContext(), R.color.color_spectro)
                        }

                        else -> {
                            Glide.with(view)
                                .load(R.drawable.spectro)
                                .into(binding.imageViewCharacterElementBottomSheet)
                            backgroundColor =
                                ContextCompat.getColor(requireContext(), R.color.color_glacio)
                        }
                    }
                }

                binding.viewCharacterNameBottomSheet.setBackgroundColor(backgroundColor)
                binding.viewCharacterLineWeaponLineBottomSheet.setBackgroundColor(backgroundColor)
                binding.viewCharacterLineMainEchoLineBottomSheet.setBackgroundColor(backgroundColor)
                binding.viewCharacterLineEchoLineBottomSheet.setBackgroundColor(backgroundColor)
                binding.viewCharacterLineEchoSetLineBottomSheet.setBackgroundColor(backgroundColor)
                binding.viewCharacterLineSubStatsLineBottomSheet.setBackgroundColor(backgroundColor)
                binding.viewCharacterLineTeamLineBottomSheet.setBackgroundColor(backgroundColor)
                binding.viewCharacterLineRatingLineBottomSheet.setBackgroundColor(backgroundColor)
                binding.viewCharacterLineUpgradeMatLineBottomSheet.setBackgroundColor(
                    backgroundColor
                )
                binding.viewCharacterLineCharAscBottomSheet.setBackgroundColor(backgroundColor)
                binding.viewCharacterLineCharSkillUpBottomSheet.setBackgroundColor(backgroundColor)

                if (character?.weapon != null) {
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

                if (character?.region != null) {
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
                binding.txtCharacterNameBottomSheet.animationXFade(Fade.FADE_IN_RIGHT)
                binding.textCharacterNameTransparentBottomSheet.animationXFade(Fade.FADE_IN_RIGHT)
                binding.textCharDesSmall.animationXFade(Fade.FADE_IN_RIGHT)
                binding.imageViewCharacterRegionBottomSheet.animationXFade(Fade.FADE_IN_UP)
                binding.imageViewCharacterBottomSheet.animationXFade(Fade.FADE_IN_UP)
//            binding.txtCharacterNameBottomSheet.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in))

//            binding.shimmerFrameLayoutCharacterDetailBottomSheet.setVisibility(View.GONE)
//            binding.characterListItemBottomSheetSub.setVisibility(View.VISIBLE)
            }
        }
    }
}