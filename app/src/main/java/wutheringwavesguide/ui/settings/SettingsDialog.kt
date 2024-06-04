package wutheringwavesguide.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import wutheringwavesguide.R
import wutheringwavesguide.databinding.DialogSettingsBinding
import wutheringwavesguide.repository.DarkMode
import wutheringwavesguide.ui.common.ClickListener
import wutheringwavesguide.util.autoCleared
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsDialog : DialogFragment() {

    var binding by autoCleared<DialogSettingsBinding>()

    private val viewModel by viewModel<SettingsViewModel>()

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dataBinding = DialogSettingsBinding.inflate(
            inflater,
            container,
            false,
        )
        binding = dataBinding
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.darkMode = viewModel.darkModeLiveData
        binding.listener = object : ClickListener {
            override fun onClick() {
                dismiss()
            }
        }
        initRadioButton()
    }

    private fun initRadioButton() {
        binding.radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.btn_default ->
                    viewModel.updateDarkMode(DarkMode.DEFAULT)

                R.id.btn_light ->
                    viewModel.updateDarkMode(DarkMode.LIGHT)

                R.id.btn_dark ->
                    viewModel.updateDarkMode(DarkMode.DARK)
            }
        }
    }

}