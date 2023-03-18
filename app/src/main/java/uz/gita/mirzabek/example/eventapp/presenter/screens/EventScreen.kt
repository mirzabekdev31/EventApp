package uz.gita.mirzabek.example.eventapp.presenter.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.mirzabek.example.eventapp.R
import uz.gita.mirzabek.example.eventapp.databinding.EventScreenBinding
import uz.gita.mirzabek.example.eventapp.presenter.adapters.EventAdapter
import uz.gita.mirzabek.example.eventapp.presenter.viewmodels.EventScreenViewModel
import uz.gita.mirzabek.example.eventapp.presenter.viewmodels.impl.EventScreenViewModelImpl
import uz.gita.mirzabek.example.eventapp.servise.EventServices


@AndroidEntryPoint
class EventScreen :Fragment(R.layout.event_screen){

    ///private val viewModel by viewModels<EventScreenViewModelImpl>()
    private val viewModel: EventScreenViewModel by viewModels<EventScreenViewModelImpl>()
    private val binding by viewBinding(EventScreenBinding::bind)
    private val adapter: EventAdapter by lazy { EventAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.eventList.adapter=adapter
        binding.eventList.layoutManager=LinearLayoutManager(requireContext())

        viewModel.allEvents.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        val intent=Intent(requireContext(),EventServices::class.java)
        requireActivity().startService(intent)

        viewModel.getAllEvents()

        adapter.setSwitchListener {
            viewModel.clickEvent(it)
        }

        binding.Share.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = "Your body here"
            val shareSub = "Your subject here"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share using"))
        }

        binding.info.setOnClickListener {
            findNavController().navigate(R.id.action_eventScreen_to_infoScreen)
        }
9
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }
}