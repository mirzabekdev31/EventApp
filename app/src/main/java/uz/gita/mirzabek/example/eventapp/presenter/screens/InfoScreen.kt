package uz.gita.mirzabek.example.eventapp.presenter.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.mirzabek.example.eventapp.R
import uz.gita.mirzabek.example.eventapp.databinding.EventScreenBinding
import uz.gita.mirzabek.example.eventapp.databinding.ScreenInfoBinding

class InfoScreen :Fragment(R.layout.screen_info){

    private val binding by viewBinding(ScreenInfoBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.BackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}