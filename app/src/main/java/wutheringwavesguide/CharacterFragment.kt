package wutheringwavesguide

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wutheringwavesguide.databinding.FragmentCharacterBinding
import wutheringwavesguide.databinding.FragmentHomeBinding

class CharacterFragment : Fragment() {
    private lateinit var binding: FragmentCharacterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCharacterBinding.inflate(inflater,container,false)
//        val inputName = requireArguments().getString("textviewData")
//        if(!TextUtils.isEmpty(inputName))
//        {
//            binding.textView5.text = inputName
//        }
        // Inflate the layout for this fragment
        return binding.root
    }
}