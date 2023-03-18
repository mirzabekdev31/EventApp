package uz.gita.mirzabek.example.eventapp.presenter.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import uz.gita.mirzabek.example.eventapp.R

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen :Fragment(R.layout.screen_splash){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            delay(2000)
            findNavController().navigate(R.id.action_splashScreen_to_eventScreen)
        }
    }
}